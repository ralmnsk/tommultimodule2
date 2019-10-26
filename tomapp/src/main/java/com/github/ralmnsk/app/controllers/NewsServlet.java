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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ={"/news"})
public class NewsServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(NewsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        paginate(req,resp);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        paginate(req,resp);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
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
        paginator.pagination(maxResults);
    }

}
