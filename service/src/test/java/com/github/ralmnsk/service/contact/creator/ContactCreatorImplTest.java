package com.github.ralmnsk.service.contact.creator;


import com.github.ralmnsk.dao.contact.ContactDaoImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.contact.ContactServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactCreatorImplTest {
    @Test
    void getContact() {
        User user=new User("testUser","123",new Date(),"usr");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        userDao.createUser(user);

        ContactService contactService= ContactServiceImpl.getInstance();
        Contact contact=new Contact("test@mail.com");
        contact.setUser(user);
        contactService.create(contact);
        ContactCreator contactCreator=new ContactCreatorImpl();

        Contact testContact=contactCreator.getContact(user,"test@mail.com");
        assertEquals(testContact.getMail(),contact.getMail());

        contactService.delete(contact.getId());
        userDao.deleteUser(user);
    }

    @Test
    void getContactNoMail() {
        User user=new User("testUser","123",new Date(),"usr");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        userDao.createUser(user);

        //ContactService contactService= ContactServiceImpl.getInstance();
        Contact contact=null;
        //contact.setUser(user);
        //contactService.create(contact);
        ContactCreator contactCreator=new ContactCreatorImpl();

        Contact testContact=contactCreator.getContact(user,null);
        assertEquals(testContact.getMail(),"no");

        //contactService.delete(contact.getId());
        userDao.deleteUser(user);
    }

    @Test
    void getContactRegex() {
        User user=new User("testUser","123",new Date(),"usr");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        userDao.createUser(user);

        ContactService contactService= ContactServiceImpl.getInstance();
        //Contact contact=new Contact("test@mail.com");
//        contact.setUser(user);
//        contactService.create(contact);
        ContactCreator contactCreator=new ContactCreatorImpl();

        Contact testContact=contactCreator.getContact(user,"test@mail.com");
        List<Contact> list=contactService.findAll(0,100);
            Contact contact=list.stream().filter(c->c.getMail().equals("test@mail.com")).collect(Collectors.toList()).get(0);
            assertTrue(contact.getMail().equals("test@mail.com"));
        //assertEquals(testContact.getMail(),"test@mail.com");
        //Contact readContact= ContactDaoImpl.getInstance().read(testContact.getId());
        contactService.delete(contact.getId());
        userDao.deleteUser(user);
    }

    @Test
    void delContact() {
        User user=new User("testUser","123",new Date(),"usr");
        UserDao userDao= UserDaoHiberImpl.getInstance();
        userDao.createUser(user);

        ContactService contactService= ContactServiceImpl.getInstance();
        Contact contact=new Contact("test@mail.com");
        contact.setUser(user);
        contactService.create(contact);
        ContactCreator contactCreator=new ContactCreatorImpl();

        Contact testContact=contactCreator.getContact(user,"test@mail.com");
        assertEquals(testContact.getMail(),contact.getMail());
        //contactService.delete(contact.getId());

        Contact testContactAfterDel=contactCreator.delContact(user);
        assertEquals(testContactAfterDel.getMail(),"no");

        userDao.deleteUser(user);
    }


//    @Test
//    void getContact() {
//        ContactCreatorImpl contactCreatorMock=mock(ContactCreatorImpl.class);
//        contactCreatorMock.getContact();
//        verify(contactCreatorMock,times(1)).getContact();
//    }
//
//    @Test
//    void delContact() {
//        ContactCreatorImpl contactCreatorMock=mock(ContactCreatorImpl.class);
//        contactCreatorMock.delContact();
//        verify(contactCreatorMock,times(1)).delContact();
//    }

//    @Test
//    void getContact1(){
//        HttpServletRequest req=new MockHttpServletRequest();
//
//        User user=new User("user","pass",new Date(),"usr");
//        Contact contact=new Contact("test@mail.com",user);
////        req.setAttribute("user",user);
////        HttpSession session=req.getSession();
////        session.setAttribute("user",user);
//       when(req.getSession().getAttribute("user")).thenReturn(user);
//        ContactCreatorImpl contactCreator=new ContactCreatorImpl(req);
//        contactCreator.getContact();
////        assertTrue(((User)(session.getAttribute("user"))).getName().equals("user"));
//
//    }
}