package com.github.ralmnsk.demo.controller;


import com.github.ralmnsk.dto.MsgDto;
import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.contact.ContactDto;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.discussion.DiscussionDto;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.contact.creator.ContactCreator;
import com.github.ralmnsk.service.deleter.NewsDeleter;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.dispute.Dispute;
import com.github.ralmnsk.service.msg.MsgCreator;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.creator.NewsCreator;
import com.github.ralmnsk.service.news.editor.NewsEditor;
import com.github.ralmnsk.service.news.updator.NewsUpdator;
import com.github.ralmnsk.service.pagination.Paginator;
import com.github.ralmnsk.service.pagination.PaginatorImpl;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    @Autowired
    private Dispute dispute;
    @Autowired
    private MsgCreator msgCreator;
    @Autowired
    private DiscussionService discussionService;

//    @Secured("USER")
    @GetMapping("/site/inform")
    public String inform(Model model,
                         HttpSession session,
                         Locale locale){ //NOT WORKING
        UserDto userDto=userService.getById((Long)session.getAttribute("userId"));
        model.addAttribute("user",userDto);
        return "inform";
    }

    @GetMapping("/site/addnews")
    public String addNews(Locale locale){
        return "addnews";
    }

    @PostMapping("/site/createnews")
    public String createNews(@RequestParam("dataNews") String dataNews,
                             @RequestParam("nameNews") String nameNews,
                             HttpServletRequest req,
                             Locale locale){
        Long userId=(Long)req.getSession().getAttribute("userId");
        creator.setUserId(userId);
        creator.setDataNews(dataNews);
        creator.setNameNews(nameNews);
        creator.newsCreate();
        return "inform";
    }

    @RequestMapping(value = "/site/mynews", method = RequestMethod.GET)
    public String news(@RequestParam(value="move",required = false) String move,
                       @RequestParam(value = "maxResults",required = false) String maxResults,
                       Model model,
                       HttpSession session,
                       Locale locale){

        int currentPage=1;
        boolean pageFlag=false;
        if(session.getAttribute("pageFlag")!=null){
            if(session.getAttribute("pageFlag").equals("myNews")){
                pageFlag=true;
            }
        }
        if((session.getAttribute("currentPage")!=null)&&pageFlag){
            currentPage=(Integer)session.getAttribute("currentPage");
        }

        boolean isMaxResultsChanged=false;
        int maxResultsCount=5;
        if (maxResults!=null){
            maxResultsCount=Integer.parseInt(maxResults);
            isMaxResultsChanged=true;
        }else{
            if((session.getAttribute("maxResults")!=null)&&pageFlag){
                maxResultsCount=(int)session.getAttribute("maxResults");
            }
        }


        int allEntitiesCount=userService.getById((Long)session.getAttribute("userId")).getNewsSetDto().size();                                                                   //=newsService.countAllNews().intValue();
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

        UserDto userDto=userService.getById((Long)session.getAttribute("userId"));
        currentPage=isMaxResultsChanged?1:currentPage;
        Map<NewsDto, UserDto> map=paginator.viewNewsOfUser((currentPage-1),maxResultsCount,userDto);
        model.addAttribute("map",map);
        session.setAttribute("pageFlag","myNews");
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pagesCount",pagesCount);
        session.setAttribute("maxResults",maxResultsCount);
        return "mynews";
    }

    @PostMapping("/site/gocontact")
    public String goContact(Model model,
                            @RequestParam("mail") String mail,
                            HttpSession session,
                            Locale locale){
        UserDto userDto=userService.getById((Long)session.getAttribute("userId"));
        if(mail!=null){
            model.addAttribute("mail",mail);
        } else{
            mail="no";
        }
        ContactDto contactDto=contactCreator.getContactDto(userDto,mail);
        model.addAttribute("contact",contactDto);
        return "contact";
    }

    @GetMapping("/site/contact")
    public String contactPost(Model model,
                              HttpSession session,
                              Locale locale){
        ContactDto contactDto=new ContactDto();
        contactDto.setMail("no");
         if(userService.getById((Long)session.getAttribute("userId")).getContactDto()!=null){
             contactDto=userService.getById((Long)session.getAttribute("userId")).getContactDto();
         }
             model.addAttribute("contact",contactDto);
        return "contact";
    }

    @PostMapping("/site/delcontact")
    public String delContact(Model model,
                             HttpServletRequest req,
                             Locale locale){
            HttpSession session=req.getSession();
            UserDto userDto=userService.getById((Long)session.getAttribute("userId"));
            ContactDto contactDto=contactCreator.delContactDto(userDto);
        model.addAttribute("contact",contactDto);
        return "contact";
    }

    @PostMapping("/site/edit")
    public String edit(Model model,
                       @RequestParam("editNewsId") Long editNewsId,
                       Locale locale){
        newsEditor.setId(editNewsId);
        model.addAttribute("news",newsEditor.newsEdit());
        model.addAttribute("editNewsId",editNewsId);
        return "editnews";
    }

    @PostMapping("/site/updatenews")
    public String update(Model model,
                         @RequestParam("editNewsId") Long editNewsId,
                         @RequestParam("nameNews") String nameNews,
                         @RequestParam("dataNews") String dataNews,
                         Locale locale){
        NewsDto newsDto=newsService.getById(editNewsId);
        newsDto.setNameNews(nameNews);
        newsDto.setDataNews(dataNews);
        newsUpdator.setNewsDto(newsDto);
        model.addAttribute("news",newsUpdator.newsUpdate());
        model.addAttribute("editNewsId",editNewsId);
        return "inform";
    }

    @PostMapping("/site/deletenews")
    public String delete(Model model,
                         HttpSession session,
                         @RequestParam("editNewsId") Long editNewsId,
                         Locale locale){
        NewsDto newsDto=newsService.getById(editNewsId);
        UserDto userDto=userService.getById((Long)session.getAttribute("userId"));
        newsDeleter.setNewsDto(newsDto);
        newsDeleter.setUserDto(userDto);
        newsDeleter.delete();
//        model.addAttribute("mapMsgUsr",null);
        return "redirect:/site/mynews";
    }

    @GetMapping("/site/comment")
    public String comment(@RequestParam(value="move",required = false) String move,
                          @RequestParam(value = "maxResults",required = false) String maxResults,
                          Model model,
                          HttpSession session,
                          Locale locale){

        Long userId=(Long)session.getAttribute("userId");

        int currentPage=1;
        boolean pageFlag=false;
        if(session.getAttribute("pageFlag")!=null){
            if(session.getAttribute("pageFlag").equals("myComments")){
                pageFlag=true;
            }
        }
        if((session.getAttribute("currentPage")!=null)&&
                (pageFlag)){
            currentPage=(Integer)session.getAttribute("currentPage");
        }

        boolean isMaxResultsChanged=false;
        int maxResultsCount=5;
        if (maxResults!=null){
            maxResultsCount=Integer.parseInt(maxResults);
            isMaxResultsChanged=true;
        }else{
            if((session.getAttribute("maxResults")!=null)&&
                    (pageFlag)){
                maxResultsCount=(int)session.getAttribute("maxResults");
            }
        }


        int allEntitiesCount=discussionService
                .readByUser(userService.getById((Long)session.getAttribute("userId"))).size();// .countAllNews().intValue(); //changes service
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
//        Map<News, User> map=paginator.viewNews((currentPage-1),maxResultsCount); //changes map

        List<DiscussionDto> list=dispute.get(userService.getById(userId));
        List<DiscussionDto> discussionList=new ArrayList<>();
        if ((list!=null)&&(list.size()>0)){
            int start=(currentPage-1)*maxResultsCount;
            int end=start+maxResultsCount-1;
            for(int i=0;i<list.size();i++){
                if((i>=start)&&(i<=end)){
                    discussionList.add(list.get(i));
                }
            }
        }

        model.addAttribute("discussionList",discussionList);
        session.setAttribute("pageFlag","myComments"); //changes
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pagesCount",pagesCount);
        session.setAttribute("maxResults",maxResultsCount);

        return "inform";
    }

    @GetMapping("/site/discuss")
    public String discussion(HttpSession session,
                             Model model,
                             @RequestParam("discussNewsId") Long discussNewsId,
                             Locale locale){
        NewsDto newsDto=newsService.getById(discussNewsId);
        Long userId=(Long)session.getAttribute("userId");
        UserDto userDto=userService.getById(userId);
        model.addAttribute("news",newsDto);

        msgCreator.setUserDto(userDto);
        msgCreator.setDiscussNewsId(discussNewsId);
//        msgCreator.setMsgText(msgText);
        Map<MsgDto, UserDto> mapMsgUsr = msgCreator.getMsgMap();

        model.addAttribute("mapMsgUsr",mapMsgUsr);
        model.addAttribute("discussNewsId",discussNewsId);
        return "discussion";
    }

    @PostMapping("/site/discuss")
    public String discussionPost(HttpSession session,
                                 Model model,
                                 @RequestParam("discussNewsId") Long discussNewsId,
                                 Locale locale){
        NewsDto newsDto=newsService.getById(discussNewsId);
        Long userId=(Long)session.getAttribute("userId");
        UserDto userDto=userService.getById(userId);
        model.addAttribute("news",newsDto);

        msgCreator.setUserDto(userDto);
        msgCreator.setDiscussNewsId(discussNewsId);
//        msgCreator.setMsgText(msgText);
        Map<MsgDto, UserDto> mapMsgUsr = msgCreator.getMsgMap();

        model.addAttribute("mapMsgUsr",mapMsgUsr);
        model.addAttribute("discussNewsId",discussNewsId);
        return "discussion";
    }

    @PostMapping("/site/msg")
    public String msg(Model model,
                      HttpSession session,
                      @RequestParam("discussNewsId") Long discussNewsId,
                      @RequestParam("msgText") String msgText,
                      Locale locale){
        NewsDto newsDto=newsService.getById(discussNewsId);
        Long userId=(Long)session.getAttribute("userId");
        UserDto userDto=userService.getById(userId);
        model.addAttribute("news",newsDto);

        msgCreator.setUserDto(userDto);
        msgCreator.setDiscussNewsId(discussNewsId);
        msgCreator.setMsgText(msgText);
        if (!msgText.equals("")){
            msgCreator.create();
        }

        model.addAttribute("mapMsgUsr",msgCreator.getMsgMap());
        model.addAttribute("discussNewsId",discussNewsId);
        return "discussion";
    }

}
