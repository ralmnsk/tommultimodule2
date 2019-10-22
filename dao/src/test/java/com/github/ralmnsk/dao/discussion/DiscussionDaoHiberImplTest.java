package com.github.ralmnsk.dao.discussion;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DiscussionDaoHiberImplTest {
    DiscussionDao discussionDao=DiscussionDaoHiberImpl.getInstance();

    public void createTestNewsInUser(){
        UserDao userDao= UserDaoHiberImpl.getInstance();
        User user=new User("testName","testPassword",new Date(),"usr");
        userDao.createUser(user);
        News news=new News("nameNews","dataNews",new Date());
        news.setUser(user);
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        newsDao.createNews(news);
    }

    @Test
    void getInstance() {
        assertNotNull(discussionDao);
    }

    @Test
    void create() {
        UserDao userDao= UserDaoHiberImpl.getInstance();
        User user=new User("testName","testPassword",new Date(),"usr");
        userDao.createUser(user);
        News news=new News("nameNews","dataNews",new Date());
        news.setUser(user);
        user.addNews(news);
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        newsDao.createNews(news);

        Discussion discussion=new Discussion();
        discussion.setNews(news);
        discussion.getUserSet().add(user);
        user.getDiscussionSet().add(discussion);
        discussionDao.create(discussion);
        userDao.updateUser(user);

        assertEquals(discussion.getId(),discussionDao.read(discussion.getId()).getId());
    }

    @Test
    void read() {
        create();
    }

    @Test
    void delete() {
        UserDao userDao= UserDaoHiberImpl.getInstance();
        User user=new User("testName","testPassword",new Date(),"usr");
        userDao.createUser(user);
        News news=new News("nameNews","dataNews",new Date());
        news.setUser(user);
        user.addNews(news);
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        newsDao.createNews(news);

        Discussion discussion=new Discussion();
        discussion.setNews(news);
        discussion.getUserSet().add(user);
        user.getDiscussionSet().add(discussion);
        discussionDao.create(discussion);
        userDao.updateUser(user);
        Long discussionId=discussion.getId();

        user.getDiscussionSet().remove(discussion);
        discussion.getUserSet().remove(user);
        userDao.updateUser(user);
        discussionDao.delete(discussionId);
        assertNull(discussionDao.read(discussionId));
    }

    @Test
    void findAll() {
    }
}