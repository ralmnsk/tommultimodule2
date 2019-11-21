package com.github.ralmnsk.demo.controller;


import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.contact.creator.ContactCreator;
import com.github.ralmnsk.service.deleter.NewsDeleter;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.creator.NewsCreator;
import com.github.ralmnsk.service.news.editor.NewsEditor;
import com.github.ralmnsk.service.news.updator.NewsUpdator;
import com.github.ralmnsk.service.pagination.Paginator;
import com.github.ralmnsk.service.pagination.PaginatorImpl;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Slf4j
@Controller
//@PreAuthorize("hasAuthority('USER')")
public class SiteController {
    @Autowired
    private NewsCreator creator;
    @Autowired
    private Paginator paginator;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContactCreator contactCreator;
    @Autowired
    private ContactService contactService;
    @Autowired
    private NewsEditor newsEditor;
    @Autowired
    private NewsUpdator newsUpdator;
    @Autowired
    private NewsDeleter newsDeleter;

//    @Secured("USER")
    @GetMapping("/site/inform")
    public String inform(Authentication authentication){ //NOT WORKING
        return "inform";
    }

    @GetMapping("/site/addnews")
    public String addNews(){
        return "addnews";
    }

    @PostMapping("/site/createnews")
    public String createNews(HttpServletRequest req){
        String dataNews=req.getParameter("dataNews");
        String nameNews=req.getParameter("nameNews");
        Long userId=(Long)req.getSession().getAttribute("userId");
        creator.setUserId(userId);
        creator.setDataNews(dataNews);
        creator.setNameNews(nameNews);
        creator.newsCreate();
        return "inform";
    }

    @RequestMapping(value = "/site/mynews", method = RequestMethod.GET)
    public String news(@RequestParam(value="move",required = false) String move,
                       @RequestParam(value = "maxResults",required = false) String maxResults, Model model, HttpServletRequest req){
        HttpSession session=req.getSession();
        int currentPage=1;
        if(session.getAttribute("currentPage")!=null){
            currentPage=(Integer)session.getAttribute("currentPage");
        }

        boolean isMaxResultsChanged=false;
        int maxResultsCount=5;
        if (maxResults!=null){
            maxResultsCount=Integer.parseInt(maxResults);
            isMaxResultsChanged=true;
        }else{
            if(session.getAttribute("maxResults")!=null){
                maxResultsCount=(int)session.getAttribute("maxResults");
            }
        }


        int allEntitiesCount=userService.getById((Long)session.getAttribute("userId")).getNewsSet().size();                                                                   //=newsService.countAllNews().intValue();
        int pagesCount=paginator.pagesCount(allEntitiesCount,maxResultsCount);


        if (move!=null){
            switch(move){
                case "previous":
                    currentPage=currentPage>1?currentPage-1:currentPage;
                    break;
                case "next":
                    currentPage=currentPage<pagesCount?currentPage=currentPage+1:currentPage;
                    break;
            }
        }

        User user=userService.getById((Long)session.getAttribute("userId"));
        currentPage=isMaxResultsChanged?1:currentPage;
        Map<News, User> map=paginator.viewNewsOfUser((currentPage-1),maxResultsCount,user);
        model.addAttribute("map",map);
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pagesCount",pagesCount);
        session.setAttribute("maxResults",maxResultsCount);
        return "mynews";
    }

    @PostMapping("/site/gocontact")
    public String goContact(HttpServletRequest req){
        HttpSession session=req.getSession();
        User user=userService.getById((Long)session.getAttribute("userId"));
        String mail="no";
        if(req.getParameter("mail")!=null){
            mail=req.getParameter("mail");
        }
        Contact contact=contactCreator.getContact(user,mail);
        session.setAttribute("contact",contact);
        return "contact";
    }

    @GetMapping("/site/contact")
    public String contactPost(HttpServletRequest req){
        HttpSession session=req.getSession();
        Contact contact=new Contact();
        contact.setMail("no");
         if(userService.getById((Long)session.getAttribute("userId")).getContact()!=null){
             contact=userService.getById((Long)session.getAttribute("userId")).getContact();
         }
             session.setAttribute("contact",contact);
        return "contact";
    }

    @PostMapping("/site/delcontact")
    public String delContact(HttpServletRequest req){
            HttpSession session=req.getSession();
            User user=userService.getById((Long)session.getAttribute("userId"));
            Contact contact=contactCreator.delContact(user);
            session.setAttribute("contact",contact);
        return "contact";
    }

    @PostMapping("/site/edit")
    public String edit(HttpServletRequest req){
        Long editNewsId=Long.parseLong(req.getParameter("editNewsId"));
        HttpSession session = req.getSession();
        newsEditor.setId(editNewsId);
        session.setAttribute("news",newsEditor.newsEdit());
        return "editnews";
    }

    @PostMapping("/site/updatenews")
    public String update(HttpServletRequest req){
        HttpSession session = req.getSession();
        News news=(News)req.getSession().getAttribute("news");
        String nameNews=req.getParameter("nameNews");
        String dataNews=req.getParameter("dataNews");
        news.setNameNews(nameNews);
        news.setDataNews(dataNews);
        newsUpdator.setNews(news);
        session.setAttribute("news",newsUpdator.newsUpdate());
        return "inform";
    }

    @PostMapping("/site/deletenews")
    public String delete(HttpServletRequest req){
        News news=(News)req.getSession().getAttribute("news");
        User user=(User)req.getSession().getAttribute("user");
        newsDeleter.setNews(news);
        newsDeleter.setUser(user);
        newsDeleter.delete();
        req.getSession().removeAttribute("mapMsgUsr");
        return "redirect:/site/mynews";
    }
}
