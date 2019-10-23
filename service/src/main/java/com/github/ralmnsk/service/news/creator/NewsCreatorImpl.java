package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NewsCreatorImpl implements NewsCreator {
    private User user;
    private String dataNews;
    private String nameNews;

    private static Logger logger= LoggerFactory.getLogger(NewsCreatorImpl.class);

    public NewsCreatorImpl(User user, String dataNews, String nameNews) {
        this.user = user;
        this.dataNews = dataNews;
        this.nameNews=nameNews;
    }

    @Override
    public News newsCreate(){
        News news=new News();

        NewsService newsService=NewsServiceImpl.getInstance();
        UserService userService= UserServiceImpl.getInstance();

        if (!dataNews.isEmpty()){
            news=new News(nameNews, dataNews, new Date());
            User readUser=userService.readUser(user);
            news.setUser(readUser);
            newsService.createNews(news);
//            readUser.getNewsSet().size();
//            readUser.addNews(news);
//            userService.updateUser(readUser);
//
//            newsService.createNews(news);

//
            logger.info(this.getClass()+" newsCreate()");
        } else {
            logger.info("news created: {} is empty", news);
        }

    return news;
    }


}
