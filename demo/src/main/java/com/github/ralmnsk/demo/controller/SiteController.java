package com.github.ralmnsk.demo.controller;


import com.github.ralmnsk.model.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
//@PreAuthorize("hasAuthority('USER')")

public class SiteController {

    @GetMapping("/site/inform")
//    @PreAuthorize("hasAuthority('USER')")

    public String inform(Principal principal){
        if(principal!=null){
            System.out.println("LOOK:"+principal);
        } else{
            System.out.println("LOOK: principal null");
        }
        return "inform";
    }


}
