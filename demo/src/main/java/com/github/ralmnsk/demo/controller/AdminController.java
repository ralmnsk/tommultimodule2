package com.github.ralmnsk.demo.controller;

import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.deleter.NewsDeleter;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.msg.MsgCreator;
import com.github.ralmnsk.service.msg.MsgService;
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
    @Autowired
    private MsgCreator msgCreator;
    @Autowired
    private MsgService msgService;

    @GetMapping("/site/inform/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping(value = "/site/inform/admin/news", method = RequestMethod.GET)
    public String news(@RequestParam(value="move",required = false) String move,
                       @RequestParam(value = "maxResults",required = false) String maxResults,
                       HttpSession session,
                       Model model){

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
        model.addAttribute("map",map);
        model.addAttribute("pageFlag","usersNews");
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pagesCount",pagesCount);
        session.setAttribute("maxResults",maxResultsCount);
        return "admin";
    }

    @PostMapping("/site/inform/admin/edit")
    public String edit(HttpServletRequest req,
                       Model model){
        HttpSession session=req.getSession();
        model.addAttribute("pageFlag","newsEdit");
        Long editNewsId=Long.parseLong(req.getParameter("editNewsId"));
        newsEditor.setId(editNewsId);
        model.addAttribute("news",newsEditor.newsEdit());
        return "admin";
    }

    @PostMapping ("/site/inform/admin/updatenews")
    public String updateNews(HttpServletRequest req,
                             @RequestParam("editNewsId") Long editNewsId,
                             @RequestParam("nameNews") String nameNews,
                             @RequestParam("dataNews") String dataNews,
                             Model model){
        HttpSession session = req.getSession();
        model.addAttribute("pageFlag","usersNews");
        News news=newsService.getById(editNewsId);

        news.setNameNews(nameNews);
        news.setDataNews(dataNews);
        newsUpdator.setNews(news);
        model.addAttribute("news",newsUpdator.newsUpdate());
        model.addAttribute("editNewsId",editNewsId);
        return "redirect:/site/inform/admin/news";
    }

    @PostMapping ("/site/inform/admin/deletenews")
    public String deleteNews(HttpSession session,
                             @RequestParam("editNewsId") Long editNewsId){
        News news=newsService.getById(editNewsId);
        User user=userService.getById((Long)session.getAttribute("userId"));
        newsDeleter.setNews(news);
        newsDeleter.setUser(user);
        newsDeleter.delete();
//        req.getSession().removeAttribute("mapMsgUsr");
        return "redirect:/site/inform/admin/news";
    }

    @GetMapping("/site/inform/admin/contact")
    public String contact (@RequestParam(value="move",required = false) String move,
                           @RequestParam(value = "maxResults",required = false) String maxResults, Model model, HttpServletRequest req){
        HttpSession session=req.getSession();

        int currentPage=1;
        boolean pageFlag=false;
        if(session.getAttribute("pageFlag")!=null){
            if(session.getAttribute("pageFlag").equals("contact")){
                pageFlag=true;
            }
        }
        if((session.getAttribute("currentPage")!=null)&&(pageFlag)){
            currentPage=(Integer)session.getAttribute("currentPage");
        }

        boolean isMaxResultsChanged=false;
        int maxResultsCount=5;
        if (maxResults!=null){
            maxResultsCount=Integer.parseInt(maxResults);
            isMaxResultsChanged=true;
        }else{
            if ((session.getAttribute("maxResults")!=null)&&pageFlag){
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

        model.addAttribute("mapContacts",mapContacts);
        session.setAttribute("pageFlag","contact");
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pagesCount",pagesCount);
        session.setAttribute("maxResults",maxResultsCount);
        return "admin";
    }

    @RequestMapping(value = "/site/inform/admin/comment", method = RequestMethod.GET)
    public String comment(@RequestParam(value="move",required = false) String move,
                          @RequestParam(value = "maxResults",required = false) String maxResults, Model model, HttpServletRequest req){
        HttpSession session=req.getSession();
        model.addAttribute("pageFlag","discussions");

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
            model.addAttribute("discussionList",discussionList);
            session.setAttribute("currentPage",currentPage);
            session.setAttribute("pagesCount",pagesCount);
            session.setAttribute("maxResults",maxResultsCount);
        return "admin";
    }

    @PostMapping("/site/inform/admin/msg")
    public String msg(HttpServletRequest req){
        HttpSession session=req.getSession();
        Long discussNewsId=(Long)session.getAttribute("discussNewsId");
        String msgText=req.getParameter("msgText");
        News news=newsService.getById(discussNewsId);
        Long userId=(Long)session.getAttribute("userId");
        User user=userService.getById(userId);
        session.setAttribute("news",news);

        msgCreator.setUser(user);
        msgCreator.setDiscussNewsId(discussNewsId);
        msgCreator.setMsgText(msgText);

        msgCreator.create();
        session.setAttribute("mapMsgUsr",msgCreator.getMsgMap());
        session.setAttribute("discussNewsId",discussNewsId);
        return "discussion";
    }

    @GetMapping("/site/inform/admin/discuss")
    public String discussion(HttpServletRequest req){
        String page=discussionProcess(req);
        return page;
    }

    @PostMapping("/site/inform/admin/discuss")
    public String discussionPost(HttpServletRequest req){
        String page=discussionProcess(req);
        return page;
    }

    private String discussionProcess(HttpServletRequest req){
        HttpSession session=req.getSession();
        Long discussNewsId=Long.parseLong(req.getParameter("discussNewsId"));
        News news=newsService.getById(discussNewsId);
        Long userId=(Long)session.getAttribute("userId");
        User user=userService.getById(userId);
        String msgText=req.getParameter("msgText");
        session.setAttribute("news",news);

        msgCreator.setUser(user);
        msgCreator.setDiscussNewsId(discussNewsId);
        msgCreator.setMsgText(msgText);
        Map<Msg, User> mapMsgUsr = msgCreator.getMsgMap();

        session.setAttribute("mapMsgUsr",mapMsgUsr);
        session.setAttribute("discussNewsId",discussNewsId);

        return "msg_admin";
    }

    @PostMapping("/site/inform/admin/msgedit")
    public String msgEdit(HttpServletRequest req){
        if (req.getParameter("msgupdate")!=null){
            Long id=Long.parseLong(req.getParameter("editMsgId"));
            msgService.update(id,req.getParameter("msgText"));
        }
        if (req.getParameter("msgdelete")!=null){
            Long id=Long.parseLong(req.getParameter("editMsgId"));
            msgService.delete(id);
        }
            Long discussNewsId=Long.parseLong(req.getParameter("discussNewsId"));
        return "redirect:/site/inform/admin/discuss?discussNewsId="+discussNewsId;
    }
}
