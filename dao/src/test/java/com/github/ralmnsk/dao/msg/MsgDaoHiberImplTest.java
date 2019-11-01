package com.github.ralmnsk.dao.msg;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MsgDaoHiberImplTest {
    private MsgDao msgDao=MsgDaoHiberImpl.getInstance();

    @Test
    void getInstance() {
        assertNotNull(msgDao);
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
    void create() {
        News news=createNewsInUser();
        Msg msg=new Msg(new Date(), "testMessage");
        msg.setNews(news);
        msgDao.create(msg);
        assertEquals(msg.getText(),msgDao.read(msg.getId()).getText());
        msgDao.delete(msg.getId());

        User user=news.getUser();
        NewsDaoHiberImpl.getInstance().deleteNews(news);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void read() {
        News news=createNewsInUser();
        Msg msg=new Msg(new Date(), "testMessage");
        msg.setNews(news);
        msgDao.create(msg);
        assertEquals(msg.getText(),msgDao.read(msg.getId()).getText());
        msgDao.delete(msg.getId());

        User user=news.getUser();
        NewsDaoHiberImpl.getInstance().deleteNews(news);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void update() {
        News news=createNewsInUser();
        Msg msg=new Msg(new Date(), "testMessage");
        msg.setNews(news);
        msgDao.create(msg);
        msgDao.update(msg.getId(),"new test text");
        assertEquals("new test text",msgDao.read(msg.getId()).getText());
        msgDao.delete(msg.getId());

        User user=news.getUser();
        NewsDaoHiberImpl.getInstance().deleteNews(news);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void delete() {
        News news=createNewsInUser();
        Msg msg=new Msg(new Date(), "testMessage");
        msg.setNews(news);
        msgDao.create(msg);
        msgDao.delete(msg.getId());
        assertNull(msgDao.read(msg.getId()));

        User user=news.getUser();
        NewsDaoHiberImpl.getInstance().deleteNews(news);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void findAll() {
        News news=createNewsInUser();
        for (int i=0;i<10;i++){
            Msg msg=new Msg(new Date(), "testMessage");
            msg.setNews(news);
            msgDao.create(msg);
        }
        List<Msg> msgList=msgDao.findAll(0,11);
        assertTrue(msgList.size()>9);
        msgDao.findAll(0,100).stream().forEach(m->msgDao.delete(m.getId()));

        User user=news.getUser();
        NewsDaoHiberImpl.getInstance().deleteNews(news);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void setUserInMsg(){
        News news=createNewsInUser();
        User user=news.getUser();
        Msg msg=new Msg(new Date(), "testMessage");
        msg.setUser(user);
        msg.setNews(news);
        msgDao.create(msg);
        assertNotNull(msg);
        assertNotNull(user);
        assertEquals(msg.getUser().getId(),user.getId());
        msgDao.delete(msg.getId());

        NewsDaoHiberImpl.getInstance().deleteNews(news);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }
}