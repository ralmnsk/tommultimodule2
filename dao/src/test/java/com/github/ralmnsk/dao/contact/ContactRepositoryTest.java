package com.github.ralmnsk.dao.contact;

import com.github.ralmnsk.model.contact.Contact;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ContactRepositoryTest {
    @Autowired
    private ContactRepository repo;
    @Test
    void countAllContacts() {
        Contact contact=new Contact();
        contact.setMail("testmail@mail.com");
        repo.save(contact);
        Long count = repo.countAllContacts();
        assertTrue(count>0);
        repo.delete(contact);
    }
}