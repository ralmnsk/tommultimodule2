package com.github.ralmnsk.dao.discussion;

import com.github.ralmnsk.dao.connection.HibernateUtil;

import com.github.ralmnsk.model.discussion.Discussion;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DiscussionDaoHiberImpl implements DiscussionDao{
    private static Logger logger= LoggerFactory.getLogger(DiscussionDaoHiberImpl.class);
    private static volatile DiscussionDao instance;

    public static DiscussionDao getInstance() {
        DiscussionDao localInstance = instance;
        if (localInstance == null) {
            synchronized (DiscussionDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DiscussionDaoHiberImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void create(Discussion discussion) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(discussion);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Discussion read(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Discussion discussion=session.get(Discussion.class,id);
        session.getTransaction().commit();
        session.close();
        return discussion;
    }


    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Discussion msg=session.get(Discussion.class,id);
        session.delete(msg);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Discussion> findAll(int firstResult, int maxResults) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<Discussion> query=session.createQuery("from disc ");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List<Discussion> discussionList=query.list();

        session.getTransaction().commit();
        session.close();
        return discussionList;
    }
}
