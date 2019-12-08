package com.github.ralmnsk.service.msg;

import com.github.ralmnsk.dao.msg.MsgRepository;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class MsgServiceImplTest {
    @Mock
    private MsgRepository repo;

    @InjectMocks
    private MsgService service=new MsgServiceImpl();

    private User user;

    private News news;

    private Msg msg;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        news=new News(1L,"name","data",new Date());
        msg=new Msg();
        msg.setText("text");
        msg.setNews(news);
        msg.setUser(user);
        msg.setDate(new Date());
        msg.setId(1L);
    }

    @Test
    void create() {
        service.create(msg);
        Mockito.verify(repo,times(1)).save(any());
    }

    @Test
    void read() {
        service.read(1L);
        Mockito.verify(repo,times(1)).getOne(any());
    }

    @Test
    void update() {
        when(repo.getOne(1L)).thenReturn(msg);
        service.update(1L,"newText");
        Mockito.verify(repo,times(1)).save(msg);
    }

    @Test
    void delete() {
        service.delete(1L);
        Mockito.verify(repo,times(1)).deleteById(1L);
    }

    @Test
    void findAll() {
        List<Msg> list=new ArrayList<>();
        list.add(msg);
        Page<Msg> page=new PageImpl<Msg>(list);
        when(repo.findAll((PageRequest.of(0,10)))).thenReturn(page);
        service.findAll(PageRequest.of(0,10));
        Mockito.verify(repo,times(1)).findAll(PageRequest.of(0,10));
    }
}