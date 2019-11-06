package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.connection.HibernateUtil;
import com.github.ralmnsk.model.user.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsDaoCounterForUserImpl implements NewsDaoCounterForUser {
    private static Logger logger= LoggerFactory.getLogger(NewsDaoHiberImpl.class);
    private static volatile NewsDaoCounterForUser instance;

    public static NewsDaoCounterForUser getInstance() {
        NewsDaoCounterForUser localInstance = instance;
        if (localInstance == null) {
            synchronized (NewsDaoCounterForUser.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new NewsDaoCounterForUserImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Integer getNewsCount(User user) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Long count=(Long)session
                .createQuery("select count(*) from News where usr_id=:id")
                .setParameter("id",user.getId())
                .getSingleResult();

        session.getTransaction().commit();
        session.close();
        return count.intValue();
    }
}

