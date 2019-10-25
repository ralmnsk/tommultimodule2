package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.service.pagination.Paginator;
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
        Paginator paginator=new PaginatorImpl(req,resp);
        paginator.pagination(5);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Paginator paginator=new PaginatorImpl(req,resp);
        paginator.pagination(5);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

//    private void pagination(HttpServletRequest req, HttpServletResponse resp, int maxResults){
//        PaginatorImpl paginator=new PaginatorImpl(req,resp);
//        String page=req.getParameter("page");
//        int pageId=0;
//        if (page!=null) {
//            pageId = Integer.parseInt(page)-1;
//            paginator.viewNews((maxResults*pageId),maxResults);
//            //5*(1-1)=0 //5*(2-1)=5 //5*(3-1)=10
//        } else {
//            paginator.viewNews((maxResults*pageId),maxResults);
//        }
//    }
}
