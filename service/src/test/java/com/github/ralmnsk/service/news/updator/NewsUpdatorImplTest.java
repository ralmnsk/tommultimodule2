package com.github.ralmnsk.service.news.updator;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.editor.NewsEditor;
import com.github.ralmnsk.service.news.editor.NewsEditorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpSession;

import java.util.Date;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class NewsUpdatorImplTest {

    @Mock
    private NewsService newsService;
    @InjectMocks
    private NewsUpdator updator=new NewsUpdatorImpl();

    private User user;

    private News news;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        news=new News(1L,"name","data",new Date());
        news.setUser(user);
        user.getNewsSet().add(news);
    }
    @Test
    void newsUpdate() {

        updator.setNews(news);
        updator.newsUpdate();
        Mockito.verify(newsService, Mockito.times(1)).updateNews(news);
    }
}