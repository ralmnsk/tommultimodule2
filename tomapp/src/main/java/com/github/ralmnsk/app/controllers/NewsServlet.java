package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;
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
import java.util.*;

@WebServlet(urlPatterns ={"/news"})
public class NewsServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(NewsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        //System.out.println("message from get");
        viewNews(req,resp);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        viewNews(req,resp);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void viewNews(HttpServletRequest req, HttpServletResponse resp) {
//        NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=NewsServiceImpl.getInstance();
//        newsService.setNewsDao(newsDao);
//        UserDao userDao=new UserDaoImpl();
        UserService userService=UserServiceImpl.getInstance();
//        userService.setUserDao(userDao);

        List<News> newsList=newsService.findAllNews(0,10);
        Collections.sort(newsList,new SortByTime());
        Map<News,User> map=new LinkedHashMap();
        if (newsList.size()>0){

        for (News news:newsList){
            User user=news.getUser();
            if(user!=null){
                Long id=user.getId();
                User readUser=userService.getById(id);
                map.put(news,readUser);

            }
        }
        HttpSession session=req.getSession();
        session.setAttribute("map",map);
        }
    }
}
