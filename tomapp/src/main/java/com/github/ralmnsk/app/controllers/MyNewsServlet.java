package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import com.github.ralmnsk.service.pagination.Paginator;
import com.github.ralmnsk.service.pagination.PaginatorImpl;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns ={"/site/mynews"})
public class MyNewsServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(MyNewsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        paginate(req,resp);
        req.getRequestDispatcher("/mynews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        paginate(req,resp);
        req.getRequestDispatcher("/mynews.jsp").forward(req, resp);
    }

    private void paginate(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session=req.getSession();
        int maxResults=5;
        if(req.getParameter("maxResults")!=null){
            maxResults=Integer.parseInt(req.getParameter("maxResults"));
            session.setAttribute("maxResults",maxResults);
        }
        if(session.getAttribute("maxResults")!=null){
            maxResults=(int)session.getAttribute("maxResults");
        }
        Paginator paginator=new PaginatorImpl(req,resp);
        paginator.paginationForUserNews(maxResults);
    }

}
