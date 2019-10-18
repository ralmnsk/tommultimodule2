package com.github.ralmnsk.dao.news;


import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

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
        readUser.getNewsList().remove(readNews);
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

    @Test
    public void deleteNewsOfUser(){
        User user=new User("testUser","123", new Date(),"usr"); //user=firstnews+second news
        User userSecond=new User("testUser2","123", new Date(),"usr"); //userSecond=thirdNews
        UserDao userDao=UserDaoHiberImpl.getInstance();
        userDao.createUser(user);
        userDao.createUser(userSecond);

        News firstNews=new News("nameNews1","dataNews1",new Date());
        News secondNews=new News("nameNews2","dataNews2",new Date());
        News thirdNews=new News("nameNews3","dataNews3",new Date());

        user.addNews(firstNews);
        user.addNews(secondNews);
        userSecond.addNews(thirdNews);

//        userDao.updateUser(user);
//        userDao.updateUser(userSecond);

        firstNews.setUser(user);
        newsDao.createNews(firstNews);

        secondNews.setUser(user);
        newsDao.createNews(secondNews);

        thirdNews.setUser(userSecond);
        newsDao.createNews(thirdNews);

        //newsDao.deleteNews(thirdNews);

        //userDao.deleteUser(userSecond);
    }
}