package com.github.ralmnsk.service.deleter;

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
        NewsService newsService= NewsServiceImpl.getInstance();
        News readNews=newsService.readNews(news);
        //UserService userService= UserServiceImpl.getInstance();
//        User readUser=userService.readUser(user);

        newsService.deleteNews(readNews);
//        userService.updateUser(readUser);
//        System.out.println("LOOK: "+readUser+" "+readUser.getNewsList());
    }
}
