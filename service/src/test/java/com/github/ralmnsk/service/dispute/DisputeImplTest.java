package com.github.ralmnsk.service.dispute;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
class DisputeImplTest {
    @Mock
    private UserService userService;
    @Mock
    private DiscussionService discussionService;

    @InjectMocks
    private Dispute dispute=new DisputeImpl();

    private User user;

    private Discussion discussion;

    private News news;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        Discussion discussion=new Discussion();
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        news=new News(1L,"name","data",new Date());
        discussion=new Discussion();
        discussion.setNews(news);
        discussion.getUserSet().add(user);
        discussion.setId(1L);
    }

    @Test
    void get() {
        dispute.get(user);
        Mockito.verify(discussionService,times(1)).readByUser(user);
    }
}