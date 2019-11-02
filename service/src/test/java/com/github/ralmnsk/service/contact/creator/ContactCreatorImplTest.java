package com.github.ralmnsk.service.contact.creator;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ContactCreatorImplTest {


//    @Test
//    void getContact() {
//        ContactCreatorImpl contactCreatorMock=mock(ContactCreatorImpl.class);
//        contactCreatorMock.getContact();
//        verify(contactCreatorMock,times(1)).getContact();
//    }

//    @Test
//    void delContact() {
//        ContactCreatorImpl contactCreatorMock=mock(ContactCreatorImpl.class);
//        contactCreatorMock.delContact();
//        verify(contactCreatorMock,times(1)).delContact();
//    }

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
}