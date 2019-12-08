package com.github.ralmnsk.service.deleter;


import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class NewsDeleterImplTest {
    @Mock
    private UserService userService;
    @Mock
    private NewsService newsService;
    @Mock
    private DiscussionService discussionService;
    @InjectMocks
    private NewsDeleter deleter=new NewsDeleterImpl();

    private User user;
    private News news;
    private Discussion discussion;
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        news=new News(1L,"name","data",new Date());
        discussion=new Discussion(1L,news);
        discussion.getUserSet().add(user);
        news.setDiscussion(discussion);
        news.setUser(user);
        user.getDiscussionSet().add(discussion);
        user.getNewsSet().add(news);

    }

    @Test
    void delete() {
        deleter.setUser(user);
        deleter.setNews(news);
        deleter.delete();
        verify(newsService,times(1))
                .deleteNews(news);
    }

}