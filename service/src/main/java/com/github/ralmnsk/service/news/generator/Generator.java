package com.github.ralmnsk.service.news.generator;

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
import com.github.ralmnsk.service.storage.StorageService;
import com.github.ralmnsk.service.storage.StorageServiceImpl;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;

import java.sql.Timestamp;

public class Generator {

    private UserDao userDao;
    private UserService userService;
    private News news;

    public void generateUserAndNews(){
        NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=new NewsServiceImpl();
        newsService.setNewsDao(newsDao);
        StorageDao storageDao=new StorageDaoImpl();
        StorageService storageService=new StorageServiceImpl();
        storageService.setStorageDao(storageDao);
        //29L put number from database manually
        for(int i=0;i<9;i++){
            News news=new News(29L,"TestNews "+i,"Text of news number "+i, new Timestamp(new java.util.Date().getTime()));
            newsService.createNews(news);
            storageDao.createStorage(29L, news.getIdNews());
        }
    }

    public static void main(String[] hello){
        Generator generator=new Generator();
        generator.generateUserAndNews();
    }
}
