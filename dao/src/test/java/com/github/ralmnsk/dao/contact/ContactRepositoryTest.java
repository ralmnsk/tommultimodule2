package com.github.ralmnsk.dao.contact;

import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ContactRepositoryTest {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ContactRepository contactRepo;
    private User user;
    private Contact contact;



    @BeforeEach
    void setUp() {
        user=new User("testUser","testPassword",new Date(),"usr");
        userRepo.save(user);
        contact=new Contact("test@mail.com");
        contactRepo.save(contact);
//        Long contactId=contact.getId();
    }

    @AfterEach
    void tearDown() {
            userRepo.delete(user);
    }



    @Test
    void countAllContacts() {
        Long count=contactRepo.countAllContacts();
        assertTrue(count>0L);
        contactRepo.delete(contact);
    }
}