package com.github.ralmnsk.service.deleter;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;

import java.util.List;

public class NewsDeleterImpl implements NewsDeleter {
    private News news;
    private User user;

    public NewsDeleterImpl(News news, User user) {
        this.news=news;
        this.user=user;
    }

    @Override
    public void delete() {
//        NewsService newsService= NewsServiceImpl.getInstance();
//        News readNews=newsService.readNews(news);
//        //UserService userService= UserServiceImpl.getInstance();
////        User readUser=userService.readUser(user);
//
//        newsService.deleteNews(readNews);
////        userService.updateUser(readUser);
////        System.out.println("LOOK: "+readUser+" "+readUser.getNewsList());
        UserDao userDao= UserDaoHiberImpl.getInstance();
        User readUser=userDao.readUser(user);
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        News readNews=newsDao.readNews(news);   //.getById(news.getIdNews());

        List<News> newsList=readUser.getNewsList(); //.remove(0);
        if ((newsList!=null)&&(newsList.size()>0)){
            for(News n:newsList){
                if (n.getIdNews()==readNews.getIdNews()){
                    System.out.println("LOOK: "+n);
                    readUser.getNewsList().remove(n);
                    userDao.updateUser(readUser);
                    newsDao.deleteNews(readNews);
                }
            }

        }
    }
}