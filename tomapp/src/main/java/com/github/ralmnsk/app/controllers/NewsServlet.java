package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.service.pagination.PaginatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={"/news"})
public class NewsServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(NewsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        //System.out.println("message from get");
        PaginatorImpl paginator=new PaginatorImpl(req,resp);
        paginator.viewNews(0,5);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaginatorImpl paginator=new PaginatorImpl(req,resp);
        paginator.viewNews(0,5);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
