package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.news.comparator.SortByTime;
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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns ={"/site/mynews"})
public class MyNewsServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(MyNewsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        viewNews(req,resp);
        req.getRequestDispatcher("/mynews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        viewNews(req,resp);
        req.getRequestDispatcher("/mynews.jsp").forward(req, resp);
    }

    private void viewNews(HttpServletRequest req, HttpServletResponse resp) {
        NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=new NewsServiceImpl();
        newsService.setNewsDao(newsDao);
        UserDao userDao=new UserDaoImpl();
        UserService userService=new UserServiceImpl();
        userService.setUserDao(userDao);

        List<News> newsList=newsService.findAllNews();
        Collections.sort(newsList,new SortByTime());
        Map<News,User> map=new LinkedHashMap();
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        for (News news:newsList){
            map.put(news,user);
        }

        for (News n:newsList) {
            logger.info("news from database:"+n.toString());
        }
        session.setAttribute("map",map);
    }
}
