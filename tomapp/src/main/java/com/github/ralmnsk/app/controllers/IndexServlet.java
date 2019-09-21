package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoImpl;
import com.github.ralmnsk.model.news.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns ="/news")
public class IndexServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(IndexServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        System.out.println("message from get");
        viewNews(req,resp);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        viewNews(req,resp);
        System.out.println("message from post");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void viewNews(HttpServletRequest req, HttpServletResponse resp) {
        NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=new NewsServiceImpl();
        newsService.setNewsDao(newsDao);
        List<News> newsList=newsService.findAllNews();
        for (News n:newsList) {
            logger.info("news from database:"+n.toString());
        }
        HttpSession session=req.getSession();
        session.setAttribute("newsList",newsList);
    }
}
