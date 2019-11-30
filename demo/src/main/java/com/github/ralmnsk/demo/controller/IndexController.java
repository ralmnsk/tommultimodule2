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
import org.springframework.web.servlet.ModelAndView;

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

//    @GetMapping(path = "/hello/{person}")
//    public String hello(@PathVariable String person, Model model){
//        model.addAttribute("person",person);
//        return "info";    //"redirect:/info";
//    }

    @GetMapping("/")
    public String index(Model model){
        if(model.getAttribute("errorRegistrationMessage")!=null){
            model.addAttribute("errorRegistrationMessage",null);
        }
        if( model.getAttribute("errorLoginPassMessage")!=null){
            model.addAttribute("errorLoginPassMessage",null);
        }
        return "redirect:/news";//"redirect:/news";    //"redirect:/info";
    }

//    @GetMapping("/security")
//    public String info(){
//        return "security";
//    }


    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news(@RequestParam(value="move",required = false) String move,
                       @RequestParam(value = "maxResults",required = false) String maxResults,
                       Model model){

        int currentPage=1;
        if(model.getAttribute("currentPage")!=null){
            currentPage=(Integer)model.getAttribute("currentPage");
        }

            boolean isMaxResultsChanged=false;
        int maxResultsCount=5;
        if (maxResults!=null){
            maxResultsCount=Integer.parseInt(maxResults);
            isMaxResultsChanged=true;
        }else{
            if(model.getAttribute("maxResults")!=null){
                maxResultsCount=(int)model.getAttribute("maxResults");
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
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pagesCount",pagesCount);
        model.addAttribute("maxResults",maxResultsCount);
        return "index";
    }

}
