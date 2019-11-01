package com.github.ralmnsk.dao.contact;

import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactDaoImplTest {
    private ContactDao contactDao=ContactDaoImpl.getInstance();
    private UserDao userDao= UserDaoHiberImpl.getInstance();
    private User user;
    private Contact contact;

    ContactDaoImplTest() {
    }

    @BeforeEach
    void setUp() {
        User newUser=new User("testUser","testPassword",new Date(),"usr");
        userDao.createUser(newUser);
        user=userDao.readUser(newUser);
        Contact newContact=new Contact("test@mail.com");
        contactDao.create(newContact);
        contact=contactDao.read(newContact.getId());
        Long contactId=contact.getId();
    }

    @AfterEach
    void tearDown() {
            userDao.deleteUser(user);
    }

    @Test
    void getInstance() {
        assertNotNull(contactDao);
        contactDao.delete(contact.getId());
    }

    @Test
    void create() {
        assertTrue(contact.getMail().equals("test@mail.com"));
        contactDao.delete(contact.getId());
    }

    @Test
    void read() {
        Contact readContact=contactDao.read(contact.getId());
        assertTrue(readContact.getMail().equals(contact.getMail()));
        contactDao.delete(readContact.getId());
    }

    @Test
    void update() {
        Contact readContact=contactDao.read(contact.getId());
        contactDao.update(readContact.getId(),"update@mail.com");
        Contact testContact=contactDao.read(readContact.getId());
        assertTrue(testContact.getMail().equals("update@mail.com"));
        contactDao.delete(testContact.getId());
    }

    @Test
    void delete() {
        contactDao.delete(contact.getId());
        Contact testContact=contactDao.read(contact.getId());
        assertNull(testContact);
    }

    @Test
    void findAll() {
        List<Contact> list=contactDao.findAll(0,10);
        assertTrue(list.size()>0);
        contactDao.delete(contact.getId());
    }
}