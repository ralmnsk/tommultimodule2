package com.github.ralmnsk.service.contact;

import com.github.ralmnsk.model.contact.Contact;

import java.util.List;

public interface ContactService {
    void create(Contact contact);
    Contact read(Long id);
    void update(Long id, String newMail);
    void delete(Long id);
    List<Contact> findAll(int firstResult, int maxResults);
}
