package com.github.ralmnsk.demo.controller;


import com.github.ralmnsk.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@Controller
//@PreAuthorize("hasAuthority('USER')")
public class SiteController {
//    @Secured("USER")
    @GetMapping("/site/inform")
    public String inform(Authentication authentication){ //NOT WORKING
//        User user=(User)authentication.getDetails();
//        System.out.println("SITE/INFORM "+user+" "
//                +user.getAuthorities());
        return "inform";
    }


}
