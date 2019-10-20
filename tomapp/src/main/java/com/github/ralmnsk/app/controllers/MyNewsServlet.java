package com.github.ralmnsk.app.controllers;

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
        viewNews(req,resp);
        req.getRequestDispatcher("/mynews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        viewNews(req,resp);
        req.getRequestDispatcher("/mynews.jsp").forward(req, resp);
    }

    private void viewNews(HttpServletRequest req, HttpServletResponse resp) {

        NewsService newsService=NewsServiceImpl.getInstance();
        UserService userService=UserServiceImpl.getInstance();
        User user=(User)req.getSession().getAttribute("user");
        User readUser=userService.readUser(user);

        if (readUser!=null){
            Set<News> newsSet=readUser.getNewsSet();
            Map<News,User> map=new LinkedHashMap();
            HttpSession session=req.getSession();
            if ((newsSet!=null)&&(newsSet.size()>0)){
                List<News> newsList=new ArrayList<News>();
                for (News news:newsSet){
                    newsList.add(news);
                }
                Collections.sort(newsList,new SortByTime());
                for (News n:newsList){
                    map.put(n,readUser);
                }
            }
                session.setAttribute("map",map);

        }
    }
}
