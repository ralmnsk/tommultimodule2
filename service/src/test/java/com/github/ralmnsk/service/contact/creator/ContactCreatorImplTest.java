package com.github.ralmnsk.service.contact.creator;


import com.github.ralmnsk.dao.contact.ContactRepository;
import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.contact.ContactServiceRepoImpl;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ContactCreatorImplTest {
    @Mock
    private UserService userService;//=new UserServiceRepoImpl(userRepository);
    @Mock
    private ContactService contactService;//=new ContactServiceRepoImpl();
    @InjectMocks
    private ContactCreator creator=new ContactCreatorImpl();

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
    void getContact() {
        when(userService.readUser(user)).thenReturn(user);
        creator.getContact(user,"mail@mail.com");
//        contactService.update(contact.getId(), contact.getMail());
        verify(contactService,times(1))
                .create(any());

        user.setContact(contact);
        creator.getContact(user,"mail2@mail.com");
        verify(contactService,times(1))
                .update(contact.getId(), contact.getMail());
    }

    @Test
    void delContact() {
        user.setContact(contact);
        when(userService.readUser(user)).thenReturn(user);
        creator.delContact(user);
        verify(contactService,times(1))
                .delete(any());
    }

}