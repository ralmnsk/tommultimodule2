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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscussionServiceImplTest {

    @Mock
    DiscussionDao dao;

    @InjectMocks
    DiscussionServiceImpl service;

    @Test
    void readByUser() {
        User user=new User("testUser","testPassword",new Date(),"usr");
        News news=new News("testNews","testData",new Date());
        Discussion discussion=new Discussion();
        discussion.setNews(news);
        discussion.getUserSet().add(user);
        List<Discussion> list=new ArrayList<>();
        list.add(discussion);
        DiscussionService discussionService=mock(DiscussionServiceImpl.class);
        discussionService.create(user,news);
        discussionService.readByUser(user);
        when(discussionService.readByUser(user)).thenReturn(list);
        assertTrue(discussionService.readByUser(user).contains(discussion));
        verify(discussionService,times(2)).readByUser(user);
    }

    @Test
    void delete() {
        doNothing().when(dao).delete(any());
        service.delete(1L);
        verify(dao,times(1)).delete(1L);
    }

    @Test
    void findAll() {
        List<Discussion> list=new ArrayList<>();
        for (int i=0;i<11;i++){
            User user=new User("testUser"+i,"testPass"+i,new Date(),"usr");
            News news=new News("testNews","testData",new Date());
            Discussion discussion=new Discussion();
            discussion.setNews(news);
            discussion.getUserSet().add(user);
            list.add(discussion);
        }
        DiscussionService discussionService=mock(DiscussionServiceImpl.class);
        when(discussionService.findAll(0,10)).thenReturn(list);
        assertTrue(discussionService.findAll(0,10).size()>9);
        discussionService.findAll(0,10);
        verify(discussionService,times(2)).findAll(0,10);
    }

    @Test
    void getInstance1() {
        assertNotNull(DiscussionServiceImpl.getInstance());
    }

    @Test
    void create1() {
        User user=new User("testUser","testPassword",new Date(),"usr");
        News news=new News("testNews","testData",new Date());
        DiscussionService discussionService=mock(DiscussionServiceImpl.class);
        discussionService.create(user,news);
        verify(discussionService,times(1)).create(user,news);
    }

    @Test
    void create(){
        User user=new User("testUser","testPassword",new Date(),"usr");
        News news=new News("testNews","testData",new Date());
        UserDao userDao= UserDaoHiberImpl.getInstance();
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        userDao.createUser(user);
        news.setUser(user);
        newsDao.createNews(news);
        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();
        DiscussionService discussionService=DiscussionServiceImpl.getInstance();
        User readUser=userDao.readUser(user);
        News readNews=newsDao.readNews(news);
        discussionService.create(readUser,readNews);
        List<Discussion> list=discussionService.readByUser(user);
        assertTrue(list.size()>0);
        Discussion discussion=list.get(0);
        assertEquals(discussion.getNews().getNameNews(),news.getNameNews());
        discussionDao.delete(discussion.getId());
        newsDao.deleteNews(news);
        userDao.deleteUser(user);
    }

//    @Test
//    void readByUser1() {
//    }

    @Test
    void delete1() {
        User user=new User("testUser","testPassword",new Date(),"usr");
        News news=new News("testNews","testData",new Date());
        UserDao userDao= UserDaoHiberImpl.getInstance();
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        userDao.createUser(user);
        news.setUser(user);
        newsDao.createNews(news);
        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();
        DiscussionService discussionService=DiscussionServiceImpl.getInstance();
        User readUser=userDao.readUser(user);
        News readNews=newsDao.readNews(news);
        discussionService.create(readUser,readNews);
        List<Discussion> list=discussionService.readByUser(user);
        assertTrue(list.size()>0);
        Discussion discussion=list.get(0);
        assertEquals(discussion.getNews().getNameNews(),news.getNameNews());
        discussionDao.delete(discussion.getId());

        List<Discussion> listAfterDel=discussionService.readByUser(user);
        assertTrue(listAfterDel.size()==0);
        newsDao.deleteNews(news);
        userDao.deleteUser(user);
    }

    @Test
    void findAll1() {

        UserDao userDao= UserDaoHiberImpl.getInstance();
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        DiscussionService discussionService= DiscussionServiceImpl.getInstance();

        User user=new User("testUser","testPass",new Date(),"usr");
        userDao.createUser(user);

        for (int i=0;i<3;i++){
            News news=new News("testNews"+i,"testData",new Date());
            news.setUser(user);
            newsDao.createNews(news);
            discussionService.create(user,news);
        }
        List<Discussion> list=discussionService.readByUser(user);
        assertTrue(list.size()==3);

        for (Discussion d:list){
            discussionService.delete(d.getId());
        }
        List<Discussion> listAll=discussionService.findAll(0,4);
        assertTrue(listAll.size()>=3);
        userDao.deleteUser(user);
    }
}