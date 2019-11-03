package com.github.ralmnsk.service.dispute;

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

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DisputeImplTest {

    @Test
    void get() {
        DisputeImpl dispute=mock(DisputeImpl.class);
        dispute.get();
        verify(dispute,times(1)).get();
    }

//    @Test
//    void getDb(){
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

    @Test
    void getDiscussions(){


    }
}