package com.github.ralmnsk.demo.controller;

import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.deleter.NewsDeleter;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.editor.NewsEditor;
import com.github.ralmnsk.service.news.updator.NewsUpdator;
import com.github.ralmnsk.service.pagination.Paginator;
import com.github.ralmnsk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private Paginator paginator;
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsEditor newsEditor;
    @Autowired
    private NewsUpdator newsUpdator;
    @Autowired
    private NewsDeleter newsDeleter;
    @Autowired
    private ContactService contactService;
    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/site/inform/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping(value = "/site/inform/admin/news", method = RequestMethod.GET)
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


        int allEntitiesCount=newsService.countAllNews().intValue();//userService.getById((Long)session.getAttribute("userId")).getNewsSet().size();                                                                   //=newsService.countAllNews().intValue();
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

//        User user=userService.getById((Long)session.getAttribute("userId"));
        currentPage=isMaxResultsChanged?1:currentPage;
        Map<News, User> map=paginator.viewNews((currentPage-1),maxResultsCount); //viewNewsOfUser((currentPage-1),maxResultsCount,user);
        session.setAttribute("map",map);
        session.setAttribute("pageFlag","usersNews");
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pagesCount",pagesCount);
        session.setAttribute("maxResults",maxResultsCount);
        return "admin";
    }

    @PostMapping("/site/inform/admin/edit")
    public String edit(HttpServletRequest req){
        HttpSession session=req.getSession();
        session.setAttribute("pageFlag","newsEdit");
        Long editNewsId=Long.parseLong(req.getParameter("editNewsId"));
        newsEditor.setId(editNewsId);
        session.setAttribute("news",newsEditor.newsEdit());
        return "admin";
    }

    @PostMapping ("/site/inform/admin/updatenews")
    public String updateNews(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute("pageFlag","usersNews");
        News news=(News)req.getSession().getAttribute("news");
        String nameNews=req.getParameter("nameNews");
        String dataNews=req.getParameter("dataNews");
        news.setNameNews(nameNews);
        news.setDataNews(dataNews);
        newsUpdator.setNews(news);
        session.setAttribute("news",newsUpdator.newsUpdate());
        return "redirect:/site/inform/admin/news";
    }

    @PostMapping ("/site/inform/admin/deletenews")
    public String deleteNews(HttpServletRequest req){
        News news=(News)req.getSession().getAttribute("news");
        User user=userService.readUser(news.getUser());
        newsDeleter.setNews(news);
        newsDeleter.setUser(user);
        newsDeleter.delete();
        req.getSession().removeAttribute("mapMsgUsr");
        return "redirect:/site/inform/admin/news";
    }

    @GetMapping("/site/inform/admin/contact")
    public String contact (@RequestParam(value="move",required = false) String move,
                           @RequestParam(value = "maxResults",required = false) String maxResults, Model model, HttpServletRequest req){
        HttpSession session=req.getSession();
        session.setAttribute("pageFlag","contact");
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

        int allEntitiesCount=contactService.countAllContacts().intValue();//userService.getById((Long)session.getAttribute("userId")).getNewsSet().size();                                                                   //=newsService.countAllNews().intValue();
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

        currentPage=isMaxResultsChanged?1:currentPage;
        List<Contact> contacts = contactService.findAll((currentPage - 1), maxResultsCount);
        Map<String, String> mapContacts=new LinkedHashMap<>();
        contactService.findAll((currentPage - 1), maxResultsCount)
        .stream().forEach(contact->mapContacts.put(contact.getUser().getName(),contact.getMail())); //viewNewsOfUser((currentPage-1),maxResultsCount,user);

        session.setAttribute("mapContacts",mapContacts);
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pagesCount",pagesCount);
        session.setAttribute("maxResults",maxResultsCount);
        return "admin";
    }

    @RequestMapping(value = "/site/inform/admin/comment", method = RequestMethod.GET)
    public String comment(@RequestParam(value="move",required = false) String move,
                          @RequestParam(value = "maxResults",required = false) String maxResults, Model model, HttpServletRequest req){
        HttpSession session=req.getSession();
        session.setAttribute("pageFlag","discussions");

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

            int allEntitiesCount=discussionService.count().intValue();//userService.getById((Long)session.getAttribute("userId")).getNewsSet().size();                                                                   //=newsService.countAllNews().intValue();
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

//        User user=userService.getById((Long)session.getAttribute("userId"));
            currentPage=isMaxResultsChanged?1:currentPage;
        List<Discussion> discussionList = discussionService.findAll((currentPage - 1), maxResultsCount);
        discussionService.findAll((currentPage - 1), maxResultsCount); //viewNewsOfUser((currentPage-1),maxResultsCount,user);
            session.setAttribute("discussionList",discussionList);
            session.setAttribute("currentPage",currentPage);
            session.setAttribute("pagesCount",pagesCount);
            session.setAttribute("maxResults",maxResultsCount);
        return "admin";
    }
}
