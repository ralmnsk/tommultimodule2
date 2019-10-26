package com.github.ralmnsk.service.contact;

import com.github.ralmnsk.dao.contact.ContactDao;
import com.github.ralmnsk.dao.contact.ContactDaoImpl;
import com.github.ralmnsk.model.contact.Contact;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private ContactDao contactDao= ContactDaoImpl.getInstance();

    private static volatile ContactService instance;

    public static ContactService getInstance() {
        ContactService localInstance = instance;
        if (localInstance == null) {
            synchronized (ContactService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ContactServiceImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void create(Contact contact) {
        contactDao.create(contact);
    }

    @Override
    public Contact read(Long id) {
        return contactDao.read(id);
    }

    @Override
    public void update(Long id, String newMail) {
        contactDao.update(id,newMail);
    }

    @Override
    public void delete(Long id) {
        contactDao.delete(id);
    }

    @Override
    public List<Contact> findAll(int firstResult, int maxResults) {
        return contactDao.findAll(firstResult,maxResults);
    }
}
