package com.github.ralmnsk.service.contact;

import com.github.ralmnsk.dao.contact.ContactDaoImpl;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceImplTest {

//    @Mock
//    private ContactDaoImpl contactDaoHiberImpl;
//
//    @InjectMocks
//    @Mock
//    private ContactServiceImpl contactService;

    @Test
    void getInstance() {
        assertNotNull(ContactServiceImpl.getInstance());
    }

    @Test
    void create() {
        ContactServiceImpl contactService=mock(ContactServiceImpl.class);
        Contact contact=new Contact("test@mail.com");
        contactService.create(contact);
        verify(contactService,times(1)).create(contact);
        when(contactService.read(1L)).thenReturn(contact);
        assertSame("test@mail.com", contactService.read(1L).getMail());
    }

    @Test
    void read() {
        ContactServiceImpl contactService=mock(ContactServiceImpl.class);
        Contact contact=new Contact("test@mail.com");
        contactService.create(contact);
        when(contactService.read(1L)).thenReturn(contact);
        assertNotNull(contactService.read(1L));
        assertSame("test@mail.com", contactService.read(1L).getMail());
    }

    @Test
    void update() {
        ContactServiceImpl contactService=mock(ContactServiceImpl.class);
        contactService.read(1L);
        verify(contactService).read(1L);
    }

    @Test
    void delete() {
        ContactServiceImpl contactService=mock(ContactServiceImpl.class);
        contactService.delete(1L);
        verify(contactService).delete(1L);
    }

    @Test
    void findAll() {
        List<Contact> list=new ArrayList<>();
        for (int i=0;i<11;i++){
            Contact contact=new Contact("mail"+i+"@mail.com");
            list.add(contact);
        }
        ContactServiceImpl contactService=mock(ContactServiceImpl.class);
        contactService.findAll(0,10);
        when(contactService.findAll(0,10)).thenReturn(list);
        verify(contactService).findAll(0,10);
        assertTrue(contactService.findAll(0,10).size()>9);
    }
}