package com.github.ralmnsk.service.contact;

import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.contact.ContactDto;

import java.util.List;

public interface ContactService {
    void create(ContactDto contactDto);
    ContactDto read(Long id);
    void update(Long id, String newMail);
    void delete(Long id);
    List<ContactDto> findAll(int page, int sized);
    Long countAllContacts();
}
