package com.github.ralmnsk.dao.news;


import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class NewsDaoHiberImplTest {
    private NewsDao newsDao=NewsDaoHiberImpl.getInstance();


    @Test
    void getInstance() {
        assertNotNull(newsDao);
    }


    @Test
    void createNews() {
        News news=new News("nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        System.out.println(foundNews);
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
    }

    @Test
    void readNews() {
        News news=new News("nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
    }

    @Test
    void updateNews() {
        News news=new News("nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        foundNews.setDataNews("changedTestData");
        newsDao.updateNews(foundNews);
        News updagtedNews=newsDao.readNews(foundNews);
        assertEquals("changedTestData",updagtedNews.getDataNews());
        newsDao.deleteNews(updagtedNews);
    }

    @Test
    void deleteNews() {
        User user=new User("testUser","123",new Date(),"usr");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        userDao.createUser(user);

        News news=new News("testNameNews","testDataNews",new Date());
        newsDao.createNews(news);

        user.addNews(news);
        news.setUser(user);

        User readUser=userDao.readUser(user);
        News readNews=newsDao.readNews(news);
        assertEquals(readNews.getNameNews(),news.getNameNews());
        assertEquals(readUser.getName(),user.getName());
        readUser.getNewsSet().remove(readNews);
        userDao.updateUser(readUser);
        newsDao.deleteNews(readNews);

        News newsAfterDeleting=newsDao.readNews(readNews);
        assertNull(newsAfterDeleting);

        User userAfterNewsDeleting=userDao.readUser(readUser);
        assertNotNull(userAfterNewsDeleting);
    }

    @Test
    void findAllNews() {
        for (int i=0; i<20;i++){
            News news=new News("nameNews"+i,"dataNews"+i,new Date());
            newsDao.createNews(news);
        }
        List<News> list=newsDao.findAllNews(0,10);
        assertTrue(list.size()>9);
        list.stream().forEach(System.out::println);
        //list.stream().forEach(news->newsDao.deleteNews(news));
    }

    @Test
    void getById() {
        News news=new News("nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News testNews=newsDao.readNews(news);
        Long newsId=testNews.getIdNews();
        News newsGetById=newsDao.getById(newsId);
        assertEquals(testNews.getNameNews(),newsGetById.getNameNews());
        assertEquals(testNews.getDataNews(),newsGetById.getDataNews());
        newsDao.deleteNews(testNews);
    }
//-------------------------------------------------------------------
    @Test
    public void createNewsInUser(){
        for (int i=1;i<4;i++){

            UserDao userDao=UserDaoHiberImpl.getInstance();
            User user=new User("testUser"+i,"123", new Date(),"usr"); //user=firstnews+second news
            userDao.createUser(user);

            News news=new News("nameNews"+i,"dataNews"+i,new Date());
            news.setUser(user);

            user.addNews(news);
            newsDao.createNews(news);
            userDao.updateUser(user);

//        newsDao.updateNews(news);
        }
    }

    @Test
    public void deleteNewsInUser(){
        createNewsInUser();

        News news=newsDao.getById(2L);
        newsDao.deleteNews(news);
    }

    @Test
    public void createMsgInNews(){
        createNewsInUser();
        for (int i=1;i<4;i++){
            Msg msg=new Msg(new Date(),"test message"+i);
            MsgDao msgDao= MsgDaoHiberImpl.getInstance();
            UserDao userDao=UserDaoHiberImpl.getInstance();
            News news=newsDao.getById(2L);
            User user=userDao.getById(3L);
            msg.setNews(news);
            msg.setUser(user);
            news.addMsg(msg);
            newsDao.updateNews(news);

        }
    }

    @Test
    public void deleteMsgInNews(){
        createMsgInNews(); //validate or create
        MsgDao msgDao= MsgDaoHiberImpl.getInstance();
        msgDao.delete(2L);
    }

    @Test
    public void deleteUser(){
        createMsgInNews();
        UserDao userDao=UserDaoHiberImpl.getInstance();
        User user=userDao.getById(2L);
        userDao.deleteUser(user);
        System.out.println();
    }
}