package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class UserDaoHiberImplTest {
    private UserDao userDao=UserDaoHiberImpl.getInstance();

    public User userInTestCreate(){
        User user=new User("testName","testPassword",new Date(),"usr");
        return user;
    }

    @Test
    public void createUser() {
        User user=userInTestCreate();
        userDao.createUser(user);
        User testUser=userDao.readUser(user);
        System.out.println(testUser);
        assertEquals(user.getName(),testUser.getName());
        assertEquals(user.getPass(),testUser.getPass());
        userDao.deleteUser(testUser);
    }

    @Test
    public void readUser() {
        User user=userInTestCreate();
        userDao.createUser(user);
        User testUser=userDao.readUser(user);
        assertEquals(user.getName(),testUser.getName());
        assertEquals(user.getPass(),testUser.getPass());
        userDao.deleteUser(testUser);
    }

    @Test
    public void updateUser() {
        User user=userInTestCreate();
        userDao.createUser(user);

        User updateUser=userDao.readUser(user);
        updateUser.setPass("ChangedPass");
        System.out.println(updateUser);
        userDao.updateUser(updateUser);
        User testUser=userDao.readUser(updateUser);
        assertEquals("ChangedPass",testUser.getPass());
        userDao.deleteUser(testUser);
    }

    @Test
    public void deleteUser() {
        User user=userInTestCreate();
        userDao.createUser(user);
        User readUser=userDao.readUser(user);
//        System.out.println("Testing: "+readUser);
        userDao.deleteUser(readUser);
        User afterTestUser=userDao.readUser(user);
        assertNull(afterTestUser);
    }

    @Test
    public void getById() {
        User user=userInTestCreate();
        userDao.createUser(user);
        User testUser=userDao.readUser(user);
        Long userId=testUser.getId();
        User userById=userDao.getById(userId);
        assertEquals(testUser.getName(),userById.getName());
        assertEquals(testUser.getPass(),userById.getPass());
        userDao.deleteUser(userById);
    }

    @Test
    public void createNewsInUser(){
        User user=userInTestCreate();
        userDao.createUser(user);

        NewsDao newsDao=NewsDaoHiberImpl.getInstance();
        News news=new News("nameNews1","dataNews1",new Date());
        newsDao.createNews(news);

        Msg msg=new Msg(new Date(),"first message");
        MsgDao msgDao= MsgDaoHiberImpl.getInstance();
        msgDao.create(msg);

        msg.setUser(user);
        msg.setNews(news);

        news.addMsg(msg);
        news.setUser(user);

        user.addNews(news);
        user.addMsg(msg);

        userDao.updateUser(user);

    }
}