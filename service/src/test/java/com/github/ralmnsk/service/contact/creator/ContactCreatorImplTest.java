package com.github.ralmnsk.service.contact.creator;

import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import com.mockrunner.mock.web.MockHttpServletRequest;
import com.mockrunner.mock.web.MockHttpSession;
import com.sun.deploy.net.HttpRequest;
import org.apache.catalina.connector.Request;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ContactCreatorImplTest {



    @Test
    void getContact() {
        ContactCreatorImpl contactCreatorMock=mock(ContactCreatorImpl.class);
        contactCreatorMock.getContact();
        verify(contactCreatorMock,times(1)).getContact();
    }

    @Test
    void delContact() {
        ContactCreatorImpl contactCreatorMock=mock(ContactCreatorImpl.class);
        contactCreatorMock.delContact();
        verify(contactCreatorMock,times(1)).delContact();
    }

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