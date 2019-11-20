package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.dao.discussion.DiscussionDao;
import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Slf4j
@Service
public class NewsCreatorImpl implements NewsCreator {
    private Long userId;
    private String dataNews;
    private String nameNews;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private DiscussionService discussionService;

    public NewsCreatorImpl(Long userId, String dataNews, String nameNews) {
        this.userId = userId;
        this.dataNews = dataNews;
        this.nameNews=nameNews;
    }

    public NewsCreatorImpl() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDataNews() {
        return dataNews;
    }

    public void setDataNews(String dataNews) {
        this.dataNews = dataNews;
    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public News newsCreate(){
        News news=new News();

        if (!dataNews.isEmpty()){
            news=new News(nameNews, dataNews, new Date());
            User readUser=userService.getById(userId);
            news.setUser(readUser);
            newsService.createNews(news);
            discussionService.create(readUser,news);
            log.info(this.getClass()+": news created:{}",news);
        } else {
            log.info("news: {} is empty", news);
        }

    return news;
    }

}
