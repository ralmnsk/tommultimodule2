package com.github.ralmnsk.dao.contact;

import com.github.ralmnsk.model.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("select count(n) from Contact n")
    Long countAllContacts();
}
