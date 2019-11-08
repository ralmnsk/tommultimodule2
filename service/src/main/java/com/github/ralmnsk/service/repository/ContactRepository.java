package com.github.ralmnsk.service.repository;

import com.github.ralmnsk.model.contact.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
