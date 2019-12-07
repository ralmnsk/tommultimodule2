package com.github.ralmnsk.service.dispute;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DisputeImplTest {
    @Test
    void get() {
        User user=new User("testUser","123",new Date(),"usr");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        userDao.createUser(user);

        News news=new News("testNews","testData",new Date());
        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
        news.setUser(user);
        newsDao.createNews(news);

        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();
        discussionDao.create(user,news);

        List<Discussion> discussionList=discussionDao.readByUser(user);
        assertTrue(discussionList.size()>0);
        Dispute dispute=new DisputeImpl();
        List<Discussion> list=dispute.get(user);
        Discussion discussion = list.stream().filter(d -> d.getNews().getNameNews().equals("testNews")).collect(Collectors.toList()).get(0);

        assertTrue(discussion.getNews().getNameNews().equals(news.getNameNews()));
        discussionDao.delete(discussion.getId());
        newsDao.deleteNews(news);
        userDao.deleteUser(user);
    }


//    @Test
//    void get() {
//        DisputeImpl dispute=mock(DisputeImpl.class);
//        User user=new User();
//        dispute.get(user);
//        verify(dispute,times(1)).get(user);
//    }
//
//
//
//    @Test
//    void get1(){
//        User user=new User("testUser","123",new Date(),"usr");
//        UserDao userDao= UserDaoHiberImpl.getInstance();
//        userDao.createUser(user);
//
//        News news=new News("testNews","testData",new Date());
//        NewsDao newsDao= NewsDaoHiberImpl.getInstance();
//        news.setUser(user);
//        newsDao.createNews(news);
//
//        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();
//        discussionDao.create(user,news);
//
//        List<Discussion> discussionList=discussionDao.readByUser(user);
//
//        assertTrue(discussionList.size()>0);
//        boolean isExist=false;
//        for(Discussion d:discussionList){
//            if(d.getNews().getIdNews()==news.getIdNews()){
//                isExist=true;
//                discussionDao.delete(d.getId());
//            }
//        }
//        assertTrue(isExist);
//        news.setUser(null);
//        newsDao.deleteNews(news);
//        userDao.deleteUser(user);
//
//    }


}