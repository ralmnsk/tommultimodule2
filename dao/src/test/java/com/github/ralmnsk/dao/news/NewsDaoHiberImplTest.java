package com.github.ralmnsk.dao.news;


import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
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

    private User userInTestCreate(){
        User user=new User("testName","testPassword",new Date(),"usr");
        return user;
    }

    private User createUserInTest(){
        UserDao userDao=UserDaoHiberImpl.getInstance();
        User user=userInTestCreate();
        userDao.createUser(user);
        return userDao.readUser(user);
    }

    private void createTestNewsInUser(){
        User user=createUserInTest();
        News news=new News("nameNews","dataNews",new Date());
        news.setUser(user);
        newsDao.createNews(news);
    }


    @Test
    void getInstance() {
        assertNotNull(newsDao);
    }


    @Test
    void createNews() {
        User user=createUserInTest();
        News news=new News("nameNews","dataNews",new Date());
        news.setUser(user);
        newsDao.createNews(news);
        News foundNews=newsDao.readNews(news);
        System.out.println(foundNews);
        assertEquals(news.getNameNews(),foundNews.getNameNews());

        newsDao.deleteNews(foundNews);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void readNews() {
        News news=new News("nameNews","dataNews",new Date());
//        newsDao.createNews(news);
        createTestNewsInUser();
        News foundNews=newsDao.readNews(news);
        User user=foundNews.getUser();
        assertEquals(news.getNameNews(),foundNews.getNameNews());
        newsDao.deleteNews(foundNews);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void updateNews() {
        News news=new News("nameNews","dataNews",new Date());
        createTestNewsInUser();
        News foundNews=newsDao.readNews(news);
        foundNews.setDataNews("changedTestData");
        User user=foundNews.getUser();
        newsDao.updateNews(foundNews);
        News updagtedNews=newsDao.readNews(foundNews);
        assertEquals("changedTestData",updagtedNews.getDataNews());
        newsDao.deleteNews(updagtedNews);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void deleteNews() {
        createTestNewsInUser();
        News news=new News("nameNews","nameNews",new Date());
        News readNews=newsDao.readNews(news);
        User user=readNews.getUser();
        newsDao.deleteNews(readNews);
        News newsAfterDel=newsDao.readNews(news);
        assertNull(newsAfterDel);
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void findAllNews() {
        User user=createUserInTest();
        for (int i=0; i<10;i++){
            News news=new News("nameNews"+i,"dataNews"+i,new Date());
            news.setUser(user);
            newsDao.createNews(news);
        }
        List<News> list=newsDao.findAllNews(0,5);
        assertTrue(list.size()>4);
        List<News> listRest=newsDao.findAllNews(0,11);
        //listRest.stream().forEach(news->newsDao.deleteNews(news));
        UserDaoHiberImpl.getInstance().deleteUser(user);
    }

    @Test
    void getById() {
        News news=new News("nameNews","dataNews",new Date());
        newsDao.createNews(news);
        News testNews=newsDao.readNews(news);
        Long newsId=testNews.getIdNews();
        News newsGetById=newsDao.getById(newsId);
        User user=newsGetById.getUser();
        assertEquals(testNews.getNameNews(),newsGetById.getNameNews());
        assertEquals(testNews.getDataNews(),newsGetById.getDataNews());
        newsDao.deleteNews(testNews);
    }

//    @Test
//    public void findNewsInUser(){
//        UserDao userDao=UserDaoHiberImpl.getInstance();
//        User user=new User("qwe","123",new Date(),"usr");
//        User readUser=userDao.readUser(user);
//        readUser.getNewsSet().stream().forEach(System.out::println);
//    }
//-------------------------------------------------------------------
//    @Test
//    public void updateExistingNews(){
//        News news=newsDao.getById(1L);
//        news.setNameNews("222");
//        news.setDataNews("222222");
//        newsDao.updateNews(news);
//        System.out.println(news);
//    }
//
//    @Test
//    public void deleteNewsWithDiscussion(){
//        UserDao userDao=UserDaoHiberImpl.getInstance();
//        //User user=userDao.getById(6L);
//        News news=newsDao.getById(66L);
//        DiscussionDaoHiberImpl.getInstance().delete(news.getDiscussion().getId());
//        newsDao.deleteNews(news);
//    }
//-------------------------------------------------------------------
//    @Test
//    public void createNewsInUser(){
//        for (int i=1;i<4;i++){
//
//            UserDao userDao=UserDaoHiberImpl.getInstance();
//            User user=new User("testUser"+i,"123", new Date(),"usr"); //user=firstnews+second news
//            userDao.createUser(user);
//
//            News news=new News("nameNews"+i,"dataNews"+i,new Date());
//            news.setUser(user);
//
//            user.addNews(news);
//            newsDao.createNews(news);
//            userDao.updateUser(user);
//
////        newsDao.updateNews(news);
//        }
//    }

//    @Test
//    public void deleteNewsInUser(){
//        createNewsInUser();
//        News testNews=new News("nameNews","dataNews",new Date());
//        News news=newsDao.readNews(testNews);
//        newsDao.deleteNews(news);
//        News readNews=newsDao.readNews(news);
//        assertNull(readNews);
//    }
//
//    @Test
//    public void createMsgInNews(){
//        createNewsInUser();
//        for (int i=1;i<4;i++){
//            msg msg=new msg(new Date(),"test message"+i);
//            MsgDao msgDao= MsgDaoHiberImpl.getInstance();
//            UserDao userDao=UserDaoHiberImpl.getInstance();
//            News news=newsDao.getById(2L);
//            User user=userDao.getById(3L);
//            msg.setNews(news);
//            msg.setUser(user);
//            news.addMsg(msg);
//            newsDao.updateNews(news);
//
//        }
//    }
//
//    @Test
//    public void deleteMsgInNews(){
//        createMsgInNews(); //validate or create
//        MsgDao msgDao= MsgDaoHiberImpl.getInstance();
//        msgDao.delete(2L);
//    }
//
//    @Test
//    public void deleteUser(){
//        createMsgInNews();
//        UserDao userDao=UserDaoHiberImpl.getInstance();
//        User user=userDao.getById(2L);
//        userDao.deleteUser(user);
//        System.out.println();
//    }
}