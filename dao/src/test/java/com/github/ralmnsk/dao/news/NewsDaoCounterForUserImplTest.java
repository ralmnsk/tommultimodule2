package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NewsDaoCounterForUserImplTest {
    private NewsDaoCounterForUser newsDaoCounterForUser=NewsDaoCounterForUserImpl.getInstance();
    @Test
    void getInstance() {
    assertNotNull(newsDaoCounterForUser);
    }

    public News createNewsInUser(){
        UserDao userDao= UserDaoHiberImpl.getInstance();
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        User user=new User("testName","testPassword",new Date(),"usr");
        userDao.createUser(user);
        News news=new News("nameNews","dataNews",new Date());
        news.setUser(user);
        newsDao.createNews(news);
        return news;
    }

    @Test
    void getNewsCount() {
        News news=createNewsInUser();
        User user= UserDaoHiberImpl.getInstance().getById(news.getUser().getId());
        int count=newsDaoCounterForUser.getNewsCount(user);
        assertTrue(count>0);

        NewsDaoHiberImpl.getInstance().deleteNews(news);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }
}