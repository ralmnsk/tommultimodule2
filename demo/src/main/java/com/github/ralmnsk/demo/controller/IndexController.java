package com.github.ralmnsk.demo.controller;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.pagination.Paginator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    private Paginator paginator;

    @GetMapping(path = "/hello/{person}")
    public String hello(@PathVariable String person, Model model){
        model.addAttribute("person",person);
        return "info";    //"redirect:/info";
    }

    @GetMapping("/")
    public String index(){
        return "index";//"redirect:/news";    //"redirect:/info";
    }

//    @GetMapping("/security")
//    public String info(){
//        return "security";
//    }

    @GetMapping("/news")
    public String news(Model model){

        Map<News, User> map=paginator.viewNews(0,10);
        model.addAttribute("map",map);
        return "index";
    }

//    private void paginate(HttpServletRequest req, HttpServletResponse resp){
//        HttpSession session=req.getSession();
//        int maxResults=5;
//        if(req.getParameter("maxResults")!=null){
//            maxResults=Integer.parseInt(req.getParameter("maxResults"));
//            session.setAttribute("maxResults",maxResults);
//        }
//        if(session.getAttribute("maxResults")!=null){
//            maxResults=(int)session.getAttribute("maxResults");
//        }
//        Paginator paginator=new PaginatorImpl(req,resp);
//        paginator.pagination(maxResults);
//    }

}
