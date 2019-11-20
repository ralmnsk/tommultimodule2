package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.dao.discussion.DiscussionDao;
import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscussionServiceImplTest {

//
//    @Test
//    void readByUser() {
//        User user=new User("testUser","testPassword",new Date(),"usr");
//        News news=new News("testNews","testData",new Date());
//        Discussion discussion=new Discussion();
//        discussion.setNews(news);
//        discussion.getUserSet().add(user);
//        List<Discussion> list=new ArrayList<>();
//        list.add(discussion);
//        DiscussionService discussionService=mock(DiscussionServiceImpl.class);
//        discussionService.create(user,news);
//        discussionService.readByUser(user);
//        when(discussionService.readByUser(user)).thenReturn(list);
//        assertTrue(discussionService.readByUser(user).contains(discussion));
//        verify(discussionService,times(2)).readByUser(user);
//    }
//
//    @Test
//    void delete() {
//        User user=new User("testUser","testPassword",new Date(),"usr");
//        News news=new News("testNews","testData",new Date());
//        DiscussionDao discussionDao=mock(DiscussionDao.class);
//        DiscussionServiceImpl discussionService=new DiscussionServiceImpl();
//        discussionService.setDiscussionDao(discussionDao);
//        doNothing().when(discussionDao).delete(any());
//        discussionService.delete(1L);
//        verify(discussionDao).delete(1L);
//    }
//
//    @Test
//    void findAll() {
//        List<Discussion> list=new ArrayList<>();
//        for (int i=0;i<11;i++){
//            User user=new User("testUser"+i,"testPass"+i,new Date(),"usr");
//            News news=new News("testNews","testData",new Date());
//            Discussion discussion=new Discussion();
//            discussion.setNews(news);
//            discussion.getUserSet().add(user);
//            list.add(discussion);
//        }
//        DiscussionService discussionService=mock(DiscussionServiceImpl.class);
//        when(discussionService.findAll(0,10)).thenReturn(list);
//        assertTrue(discussionService.findAll(0,10).size()>9);
//        discussionService.findAll(0,10);
//        verify(discussionService,times(2)).findAll(0,10);
//    }
//
//    @Test
//    void getInstance1() {
//        assertNotNull(DiscussionServiceImpl.getInstance());
//    }
//
//    @Test
//    void create1() {
//        User user=new User("testUser","testPassword",new Date(),"usr");
//        News news=new News("testNews","testData",new Date());
//        DiscussionDao discussionDao=mock(DiscussionDao.class);
//        DiscussionServiceImpl discussionService=new DiscussionServiceImpl();
//        discussionService.setDiscussionDao(discussionDao);
//        doNothing().when(discussionDao).create(any(),any());
//        discussionService.create(user,news);
//        verify(discussionDao).create(user,news);
//    }
//
//    @Test
//    void create(){
//        User user=new User("testUser","testPassword",new Date(),"usr");
//        News news=new News("testNews","testData",new Date());
//        UserDao userDao= UserDaoHiberImpl.getInstance();
//        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
//        userDao.createUser(user);
//        news.setUser(user);
//        newsDao.createNews(news);
//        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();
//        DiscussionService discussionService=DiscussionServiceImpl.getInstance();
//        User readUser=userDao.readUser(user);
//        News readNews=newsDao.readNews(news);
//        discussionService.create(readUser,readNews);
//        List<Discussion> list=discussionService.readByUser(user);
//        assertTrue(list.size()>0);
//        Discussion discussion=list.get(0);
//        assertEquals(discussion.getNews().getNameNews(),news.getNameNews());
//        discussionDao.delete(discussion.getId());
//        newsDao.deleteNews(news);
//        userDao.deleteUser(user);
//    }
//
//    @Test
//    void readByUser1() {
//        User user=new User("testUser","testPassword",new Date(),"usr");
//        User readUser=new User("testUser","testPassword",new Date(),"usr");
//        readUser.setId(1L);
//        News news=new News("testNews","testData",new Date());
//        Discussion discussion=new Discussion();
//        discussion.setNews(news);
//        discussion.setId(1L);
//
//        DiscussionDao discussionDao=mock(DiscussionDao.class);
//        DiscussionServiceImpl discussionService=new DiscussionServiceImpl();
//        discussionService.setDiscussionDao(discussionDao);
//        List<Discussion> list=new ArrayList<>();
//        list.add(discussion);
//
//        when(discussionDao.readByUser(user)).thenReturn(list);
//        List<Discussion> dList=discussionService.readByUser(user);
//        verify(discussionDao).readByUser(user);
//        assertTrue(dList.contains(discussion));
//    }
//
//    @Test
//    void delete1() {
//        User user=new User("testUser","testPassword",new Date(),"usr");
//        News news=new News("testNews","testData",new Date());
//        UserDao userDao= UserDaoHiberImpl.getInstance();
//        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
//        userDao.createUser(user);
//        news.setUser(user);
//        newsDao.createNews(news);
//        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();
//        DiscussionService discussionService=DiscussionServiceImpl.getInstance();
//        User readUser=userDao.readUser(user);
//        News readNews=newsDao.readNews(news);
//        discussionService.create(readUser,readNews);
//        List<Discussion> list=discussionService.readByUser(user);
//        assertTrue(list.size()>0);
//        Discussion discussion=list.get(0);
//        assertEquals(discussion.getNews().getNameNews(),news.getNameNews());
//        discussionDao.delete(discussion.getId());
//
//        List<Discussion> listAfterDel=discussionService.readByUser(user);
//        assertTrue(listAfterDel.size()==0);
//        newsDao.deleteNews(news);
//        userDao.deleteUser(user);
//    }
//
//    @Test
//    void findAll1() {
//
//        UserDao userDao= UserDaoHiberImpl.getInstance();
//        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
//        DiscussionService discussionService= DiscussionServiceImpl.getInstance();
//
//        User user=new User("testUser","testPass",new Date(),"usr");
//        userDao.createUser(user);
//
//        for (int i=0;i<3;i++){
//            News news=new News("testNews"+i,"testData",new Date());
//            news.setUser(user);
//            newsDao.createNews(news);
//            discussionService.create(user,news);
//        }
//        List<Discussion> list=discussionService.readByUser(user);
//        assertTrue(list.size()==3);
//
//        for (Discussion d:list){
//            discussionService.delete(d.getId());
//        }
//        List<Discussion> listAll=discussionService.findAll(0,4);
//        assertTrue(listAll.size()>=3);
//        userDao.deleteUser(user);
//    }
}