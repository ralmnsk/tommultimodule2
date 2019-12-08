package com.github.ralmnsk.service.news.editor;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
class NewsEditorImplTest {
    @Mock
    private NewsService newsService;
    @InjectMocks
    private NewsEditor editor=new NewsEditorImpl();

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
    void getId() {
        NewsEditorImpl newsEditor=new NewsEditorImpl(1L);
        assertEquals(newsEditor.getId(),1L);
    }

    @Test
    void setId() {
        NewsEditorImpl newsEditor=new NewsEditorImpl(1L);
        newsEditor.setId(2L);
        assertEquals(newsEditor.getId(),2L);
    }

    @Test
    void newsEdit() {
        editor.setId(1L);
        editor.newsEdit();
        Mockito.verify(newsService, Mockito.times(1)).getById(any());
    }

}