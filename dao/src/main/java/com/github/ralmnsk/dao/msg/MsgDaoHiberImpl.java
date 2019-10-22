package com.github.ralmnsk.dao.msg;

import com.github.ralmnsk.dao.connection.HibernateUtil;
import com.github.ralmnsk.model.msg.Msg;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MsgDaoHiberImpl implements MsgDao{
    private static Logger logger= LoggerFactory.getLogger(MsgDaoHiberImpl.class);
    private static volatile MsgDao instance;

    public static MsgDao getInstance() {
        MsgDao localInstance = instance;
        if (localInstance == null) {
            synchronized (MsgDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MsgDaoHiberImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void create(Msg msg) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(msg);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Msg read(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Msg msg=session.get(Msg.class,id);
        session.getTransaction().commit();
        session.close();
        return msg;
    }

    @Override
    public void update(Long id, String newText) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Msg msg=session.get(Msg.class,id);
        msg.setText(newText);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Msg msg=session.get(Msg.class,id);
        session.delete(msg);
//        msg.getNews().getMsgSet().remove(msg);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Msg> findAll(int firstResult, int maxResults) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<Msg> query=session.createQuery("from msg ");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List<Msg> msgList=query.list();

        session.getTransaction().commit();
        session.close();
        return msgList;
    }
}
