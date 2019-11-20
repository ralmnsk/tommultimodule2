package com.github.ralmnsk.demo.controller;


import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.creator.NewsCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
//@PreAuthorize("hasAuthority('USER')")
public class SiteController {
    @Autowired
    private NewsCreator creator;
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



}
