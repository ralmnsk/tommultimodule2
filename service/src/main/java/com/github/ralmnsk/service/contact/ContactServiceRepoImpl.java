package com.github.ralmnsk.service.contact;

import com.github.ralmnsk.dao.contact.ContactDao;
import com.github.ralmnsk.dao.contact.ContactDaoImpl;
import com.github.ralmnsk.dao.contact.ContactRepository;
import com.github.ralmnsk.model.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceRepoImpl implements ContactService {

    @Autowired
    private ContactRepository repo;



    @Override
    public void create(Contact contact) {
        repo.save(contact);
    }

    @Override
    public Contact read(Long id) {
        return repo.getOne(id);
    }

    @Override
    public void update(Long id, String newMail) {
        Contact contact=repo.getOne(id);
        contact.setMail(newMail);
        repo.save(contact);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Contact> findAll(int firstResult, int maxResults) {
        return repo.findAll(PageRequest.of(firstResult,maxResults)).getContent();
    }
}
