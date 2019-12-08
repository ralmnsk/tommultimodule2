package com.github.ralmnsk.service.contact;

import com.github.ralmnsk.dao.contact.ContactRepository;
import com.github.ralmnsk.dao.news.NewsRepository;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceRepoImpl;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ContactServiceRepoImplTest {
    @Mock
    private ContactRepository repo;

    @InjectMocks
    private ContactService service=new ContactServiceRepoImpl();

    private User user;

    private Contact contact;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        contact=new Contact();
        contact.setUser(user);
        contact.setId(1L);
        contact.setMail("mail@mail.com");
    }

    @Test
    void create() {
        service.create(contact);
        Mockito.verify(repo,times(1)).save(contact);
    }

    @Test
    void read() {
        service.read(contact.getId());
        Mockito.verify(repo,times(1)).getOne(contact.getId());
    }

    @Test
    void update() {
        when(repo.getOne(1L)).thenReturn(contact);
        service.update(1L,"text@mail.com");
        Mockito.verify(repo,times(1)).save(contact);
    }

    @Test
    void delete() {
        service.delete(contact.getId());
        Mockito.verify(repo,times(1)).deleteById(contact.getId());
    }

    @Test
    void findAll() {
        List<Contact> list=new ArrayList<>();
        list.add(contact);
        Page<Contact> page=new PageImpl<Contact>(list);
        when(repo.findAll((PageRequest.of(0,10)))).thenReturn(page);
        service.findAll(0,10);
        Mockito.verify(repo,times(1)).findAll(PageRequest.of(0,10));
    }

    @Test
    void countAllContacts() {
        when(repo.countAllContacts()).thenReturn(10L);
        service.countAllContacts();
        Mockito.verify(repo,times(1)).countAllContacts();
    }
}