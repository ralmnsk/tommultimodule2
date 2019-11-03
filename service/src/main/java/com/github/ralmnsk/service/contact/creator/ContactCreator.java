package com.github.ralmnsk.service.contact.creator;

import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;

public interface ContactCreator {
    Contact getContact(User user, String mail);
    Contact delContact(User user);
}
