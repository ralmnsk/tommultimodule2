package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscussionServiceImplTest {

    @Test
    void getInstance() {
        assertNotNull(DiscussionServiceImpl.getInstance());
    }

    @Test
    void create() {
        User user=new User("testUser","testPassword",new Date(),"usr");
        News news=new News("testNews","testData",new Date());
        DiscussionService discussionService=mock(DiscussionServiceImpl.class);
        discussionService.create(user,news);
        verify(discussionService,times(1)).create(user,news);
    }

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
        DiscussionService discussionService=mock(DiscussionServiceImpl.class);
        discussionService.delete(1L);
        verify(discussionService,times(1)).delete(1L);
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
}