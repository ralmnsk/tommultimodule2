package com.github.ralmnsk.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class ErrorPageController implements ErrorController {
    private final static String PATH = "/error";
    @Override
    @RequestMapping(PATH)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String getErrorPath() {
        // TODO Auto-generated method stub

        return PATH;
    }

    @GetMapping("/error")
    public String customError() {
        return "error";
    }

//    @RequestMapping("/error")
//    public ModelAndView handleError(){
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("error");
//        return modelAndView;
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
