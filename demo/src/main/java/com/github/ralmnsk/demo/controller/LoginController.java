package com.github.ralmnsk.demo.controller;

import com.github.ralmnsk.demo.security.RegistrationForm;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.authorization.Authorization;
import com.github.ralmnsk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public LoginController(
        UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginController(){}
//    @GetMapping("/login")
//    public String login(){
//        return "login";    //"redirect:/info";
//    }
//
//    @GetMapping("/welcome")
//    public String welcome(){
//        return "welcome";

    @PostMapping("/authorization")
    public String loginPost(RegistrationForm form, HttpServletRequest req){

        User authUser=form.toUserNoPassEncoder();
        UserDetails userDetails=userDetailsService.loadUserByUsername(authUser.getName());
        if(userDetails!=null){
                return "welcome";
            }
        String errorLoginPassMessage="Неверный логин или пароль";
        req.getSession().setAttribute("errorLoginPassMessage",errorLoginPassMessage);
        return "login";
    }



}
