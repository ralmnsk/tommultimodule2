package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper mapper;

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
    public NewsDto newsCreate(){
        NewsDto newsDto=new NewsDto();

        if (!dataNews.isEmpty()){
            newsDto=new NewsDto(nameNews, dataNews, new Date());
            UserDto readUserDto=userService.getById(userId);
            newsDto.setUserDto(readUserDto);
            newsService.createNews(newsDto);
            discussionService.create(readUserDto,newsDto);
            log.info(this.getClass()+": news created:{}",newsDto);
        } else {
            log.info("news: {} is empty", newsDto);
        }

    return newsDto;
    }

}
