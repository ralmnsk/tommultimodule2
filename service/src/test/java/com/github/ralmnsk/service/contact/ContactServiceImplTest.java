package com.github.ralmnsk.service.contact;

import com.github.ralmnsk.dao.contact.ContactDao;
import com.github.ralmnsk.dao.contact.ContactDaoImpl;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
class ContactServiceImplTest {

//    @Mock
//    private ContactDao contactDao;
//
//    @InjectMocks
//    private ContactServiceImpl contactService=new ContactServiceImpl();


//    @Test
//    void getInstance() {
//        assertNotNull(ContactServiceImpl.getInstance());
//    }
//
//    @Test
//    void create() {
//        ContactDao contactDao=mock(ContactDao.class);
//        Contact contact=new Contact("test@mail.com");
//        ContactServiceImpl contactService=new ContactServiceImpl();
//        contactService.setContactDao(contactDao);
//        doNothing().when(contactDao).create(any());
//        contactService.create(contact);
//        verify(contactDao).create(contact);
//    }
//
//    @Test
//    void read() {
//        ContactDao contactDao=mock(ContactDao.class);
//        Contact contact=new Contact("test@mail.com");
//        ContactServiceImpl contactService=new ContactServiceImpl();
//        contactService.setContactDao(contactDao);
//        when(contactDao.read(1L)).thenReturn(contact);
//        contactService.read(1L);
//        assertSame("test@mail.com", contactService.read(1L).getMail());
//    }
//
//    @Test
//    void update() {
//        ContactDao contactDao=mock(ContactDao.class);
////        Contact contact=new Contact("test@mail.com");
//        ContactServiceImpl contactService=new ContactServiceImpl();
//        contactService.setContactDao(contactDao);
//        doNothing().when(contactDao).update(any(),any());
//        contactService.update(1L,"test@mail.com");
//        verify(contactDao).update(1L,"test@mail.com");
////        ContactServiceImpl contactService=mock(ContactServiceImpl.class);
////        contactService.read(1L);
////        verify(contactService).read(1L);
//    }
//
//    @Test
//    void delete() {
//        ContactDao contactDao=mock(ContactDao.class);
//        Contact contact=new Contact("test@mail.com");
//        ContactServiceImpl contactService=new ContactServiceImpl();
//        contactService.setContactDao(contactDao);
//        doNothing().when(contactDao).delete(any());
//        contactService.delete(1L);
//        verify(contactDao).delete(1L);
////        ContactServiceImpl contactService=mock(ContactServiceImpl.class);
////        contactService.delete(1L);
////        verify(contactService).delete(1L);
//    }
//
//    @Test
//    void findAll() {
//        List<Contact> list=new ArrayList<>();
//        for (int i=0;i<11;i++){
//            Contact contact=new Contact("mail"+i+"@mail.com");
//            list.add(contact);
//        }
//        ContactServiceImpl contactService=mock(ContactServiceImpl.class);
//        contactService.findAll(0,10);
//        when(contactService.findAll(0,10)).thenReturn(list);
//        verify(contactService).findAll(0,10);
//        assertTrue(contactService.findAll(0,10).size()>9);
//    }
//
//    @Test
//    void create1() {
//        ContactService contactService=ContactServiceImpl.getInstance();
//        Contact contact=new Contact("test@mail.com");
//        contactService.create(contact);
//        Contact readContact=contactService.read(contact.getId());
//        assertEquals(contact.getMail(),readContact.getMail());
//        contactService.delete(contact.getId());
//    }
//
//    @Test
//    void update1() {
//        ContactService contactService=ContactServiceImpl.getInstance();
//        Contact contact=new Contact("test@mail.com");
//        contactService.create(contact);
//        contactService.update(contact.getId(),"test2@mail.com");
//        Contact readContact=contactService.read(contact.getId());
//        assertEquals(readContact.getMail(),"test2@mail.com");
//        contactService.delete(contact.getId());
//    }
//
//    @Test
//    void findAll1() {
//        ContactService contactService=ContactServiceImpl.getInstance();
//        Contact contact=new Contact("test@mail.com");
//        contactService.create(contact);
//        assertTrue(contactService.findAll(0,2).size()>=0);
//        contactService.delete(contact.getId());
//    }
}