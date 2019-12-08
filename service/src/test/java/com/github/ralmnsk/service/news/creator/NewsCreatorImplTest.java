package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.msg.MsgCreator;
import com.github.ralmnsk.service.msg.MsgCreatorImpl;
import com.github.ralmnsk.service.msg.MsgService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class NewsCreatorImplTest {

    @Mock
    private NewsService newsService;
    @Mock
    private DiscussionService discussionService;
    @Mock
    private UserService userService;
    @InjectMocks
    private NewsCreator creator=new NewsCreatorImpl();

    private User user;
    private News news;
    private Discussion discussion;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        news=new News(1L,"name","data",new Date());
        discussion=new Discussion();
        discussion.setNews(news);
        discussion.getUserSet().add(user);
        discussion.setId(1L);
    }

    @Test
    void newsCreate() {
        when(userService.getById(user.getId())).thenReturn(user);
        when(discussionService.create(user,news)).thenReturn(true);
        creator.setDataNews("data");
        creator.setNameNews("name");
        creator.setUserId(user.getId());
        creator.newsCreate();
        Mockito.verify(newsService,times(1)).createNews(any());
    }
}