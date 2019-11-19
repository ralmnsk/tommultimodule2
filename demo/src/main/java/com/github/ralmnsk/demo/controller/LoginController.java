package com.github.ralmnsk.demo.controller;

import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.demo.security.RegistrationForm;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.authorization.Authorization;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    public LoginController(
        UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/login")
    public String login(){
        return "login";    //"redirect:/info";
    }
//
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PostMapping("/login")
    public String loginPost(RegistrationForm form, HttpServletRequest req){

        User authUser=form.toUserNoPassEncoder();
        UserDetails userDetails=userDetailsService.loadUserByUsername(authUser.getName());
        if(userDetails!=null){
            if(passwordEncoder.matches(authUser.getPass(),userDetails.getPassword())){
                User user=userService.readUser(authUser);
                req.getSession().setAttribute("user",user);
                return "welcome";
            }
        }
        String errorLoginPassMessage="Неверный логин или пароль";
        req.getSession().setAttribute("errorLoginPassMessage",errorLoginPassMessage);
        return "login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//public String loginPage(@RequestParam(value = "error", required = false) String error,
//                        @RequestParam(value = "logout", required = false) String logout,
//                        Model model) {
//    String errorLoginPassMessage = null;
//    if(error != null) {
//        errorLoginPassMessage = "Username or Password is incorrect !!";
//    }
//    if(logout != null) {
//        errorLoginPassMessage = "You have been successfully logged out !!";
//    }
//    model.addAttribute("errorLoginPassMessage", errorLoginPassMessage);
//    return "login";
//}

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }


}
