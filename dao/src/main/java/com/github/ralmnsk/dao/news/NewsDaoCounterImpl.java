package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.dao.connection.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

public class NewsDaoCounterImpl implements NewsDaoCounter {
    private static Logger logger= LoggerFactory.getLogger(NewsDaoHiberImpl.class);
    private static volatile NewsDaoCounter instance;

    public static NewsDaoCounter getInstance() {
        NewsDaoCounter localInstance = instance;
        if (localInstance == null) {
            synchronized (NewsDaoCounter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new NewsDaoCounterImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Integer getNewsCount() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        BigInteger count=(BigInteger)session.createSQLQuery("select count(*) from nws").getSingleResult();
        session.getTransaction().commit();
        session.close();
        Integer convertedCount=count.intValue();
        return convertedCount;
    }
}

