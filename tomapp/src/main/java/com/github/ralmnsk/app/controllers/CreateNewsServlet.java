package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.creator.NewsCreator;
import com.github.ralmnsk.service.news.creator.NewsCreatorImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/site/createnews")
public class CreateNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        processReq(req,resp);
    }

    private void processReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=(User)req.getSession().getAttribute("user");
        String dataNews=req.getParameter("dataNews");
        String nameNews=req.getParameter("nameNews");
        NewsCreator newsCreator=new NewsCreatorImpl(user,dataNews,nameNews);
        newsCreator.newsCreate();
        req.getRequestDispatcher("/inform.jsp").forward(req, resp);
    }
}
