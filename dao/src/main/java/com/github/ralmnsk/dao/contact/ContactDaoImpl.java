package com.github.ralmnsk.dao.contact;

import com.github.ralmnsk.dao.connection.HibernateUtil;
import com.github.ralmnsk.dao.msg.MsgDao;
import com.github.ralmnsk.dao.msg.MsgDaoHiberImpl;
import com.github.ralmnsk.model.contact.Contact;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ContactDaoImpl implements ContactDao {
    private static Logger logger= LoggerFactory.getLogger(MsgDaoHiberImpl.class);
    private static volatile ContactDao instance;

    public static ContactDao getInstance() {
        ContactDao localInstance = instance;
        if (localInstance == null) {
            synchronized (ContactDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ContactDaoImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void create(Contact contact) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Contact read(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Contact contact=session.get(Contact.class,id);
        session.getTransaction().commit();
        session.close();
        return contact;
    }

    @Override
    public void update(Long id, String newMail) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Contact contact=session.get(Contact.class,id);
        contact.setMail(newMail);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Contact contact=session.get(Contact.class,id);
        session.delete(contact);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Contact> findAll(int firstResult, int maxResults) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<Contact> query=session.createQuery("from Contact ");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List<Contact> contactList=query.list();

        session.getTransaction().commit();
        session.close();
        return contactList;
    }
}
