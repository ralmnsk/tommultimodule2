package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoImpl;
import com.github.ralmnsk.dao.storage.StorageDao;
import com.github.ralmnsk.dao.storage.StorageDaoImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.news.comparator.SortByTime;
import com.github.ralmnsk.service.storage.StorageService;
import com.github.ralmnsk.service.storage.StorageServiceImpl;
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
//        NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=NewsServiceImpl.getInstance();
//        newsService.setNewsDao(newsDao);
//        UserDao userDao=new UserDaoImpl();
        UserService userService=UserServiceImpl.getInstance();
//        userService.setUserDao(userDao);
//        StorageDao storageDao=new StorageDaoImpl();
        StorageService storageService=StorageServiceImpl.getInstance();
//        storageService.setStorageDao(storageDao);

        User user=(User)req.getSession().getAttribute("user");
        List<Long> list=storageService.getNewsIdByUserId(user.getId());
        List<News> newsList=list.stream().map(newsId->newsService.getById(newsId)).collect(Collectors.toList());

        Collections.sort(newsList,new SortByTime());
        Map<News,User> map=new LinkedHashMap();
        HttpSession session=req.getSession();

        for (News news:newsList){
            map.put(news,user);
        }

        for (News n:newsList) {
            logger.info("news from database:"+n.toString());
        }
        session.setAttribute("map",map);
    }
}
