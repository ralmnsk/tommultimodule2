package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.deleter.NewsDeleter;
import com.github.ralmnsk.service.deleter.NewsDeleterImpl;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/site/deletenews")
public class DelNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
        req.getRequestDispatcher("/site/mynews").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
        req.getRequestDispatcher("/site/mynews").forward(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        News news=(News)req.getSession().getAttribute("news");
        User user=(User)req.getSession().getAttribute("user");
        NewsDeleter deleter=new NewsDeleterImpl(news, user);
        deleter.delete();
        req.getSession().removeAttribute("mapMsgUsr");
    }
}