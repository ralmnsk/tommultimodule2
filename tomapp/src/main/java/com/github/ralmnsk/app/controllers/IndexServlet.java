package com.github.ralmnsk.app.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns ="/home")
public class IndexServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(IndexServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        //resp.sendRedirect(req.getContextPath() +"/news");
//        req.getRequestDispatcher(req.getContextPath() +"/news").forward(req,resp);
//        System.out.println(req.getContextPath() +"/news");
        getServletContext().getRequestDispatcher("/news").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher(req.getContextPath() +"/news").forward(req,resp);
//        System.out.println(req.getContextPath() +"/news");
        getServletContext().getRequestDispatcher("/news").forward(req, resp);
    }

}
