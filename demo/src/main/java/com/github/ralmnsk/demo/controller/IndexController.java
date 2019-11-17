package com.github.ralmnsk.demo.controller;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.pagination.Paginator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    private Paginator paginator;
    @Autowired
    private NewsService newsService;

    @GetMapping(path = "/hello/{person}")
    public String hello(@PathVariable String person, Model model){
        model.addAttribute("person",person);
        return "info";    //"redirect:/info";
    }

    @GetMapping("/")
    public String index(HttpServletRequest req){
        if(req.getSession().getAttribute("errorRegistrationMessage")!=null){
            req.getSession().removeAttribute("errorRegistrationMessage");
        }
        if( req.getSession().getAttribute("errorLoginPassMessage")!=null){
            req.getSession().removeAttribute("errorLoginPassMessage");
        }
        return "redirect:/news";//"redirect:/news";    //"redirect:/info";
    }

//    @GetMapping("/security")
//    public String info(){
//        return "security";
//    }


    @RequestMapping(value = "/news", method = RequestMethod.GET)
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


        int allEntitiesCount=newsService.countAllNews().intValue();
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
        Map<News, User> map=paginator.viewNews((currentPage-1),maxResultsCount);
        model.addAttribute("map",map);
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pagesCount",pagesCount);
        session.setAttribute("maxResults",maxResultsCount);
        return "index";
    }

}
