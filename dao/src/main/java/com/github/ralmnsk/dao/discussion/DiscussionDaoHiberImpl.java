package com.github.ralmnsk.dao.discussion;

import com.github.ralmnsk.dao.connection.HibernateUtil;

import com.github.ralmnsk.model.discussion.Discussion;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    public void create(User user, News news) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User readUser=session.get(User.class,user.getId());
        News readNews=session.get(News.class,news.getIdNews());


        if(!isExist(news, session)){
            Discussion discussion=new Discussion();
            discussion.setNews(readNews);
            readNews.setDiscussion(discussion);
            discussion.getUserSet().add(readUser);
            readUser.getDiscussionSet().add(discussion);
            session.save(discussion);
        }
        session.getTransaction().commit();
        session.close();
    }

    private boolean isExist(News news, Session session) {
        boolean isExist=false;
        CriteriaBuilder cb=session.getCriteriaBuilder();
        CriteriaQuery<Discussion> criteria = cb.createQuery(Discussion.class);
        Root<Discussion> d = criteria.from(Discussion.class);
        criteria.select(d).where(
                //cb.equal(d.<News>get("news"),discussion.getNews())
                cb.equal(d.get("news"),news.getIdNews())
        );
        List<Discussion> resultList = session.createQuery(criteria).getResultList();
        if (resultList.size()>0){
            return true;
        }
        return isExist;
    }

    @Override
    public List<Discussion> readByUser(User user) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Discussion> criteria = builder.createQuery(Discussion.class);
        Root<Discussion> root = criteria.from(Discussion.class);
        criteria.select(root).where(
                builder.isMember(
                        user,
                        root.<Collection<User>>get("userSet")
                )
        );
        List<Discussion> resultList = session.createQuery(criteria).getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }


    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Discussion discussion=session.get(Discussion.class,id);

        discussion.getUserSet().stream()
                .map(user -> user.getDiscussionSet())
                .forEach(set->set.remove(discussion));
        discussion.getUserSet().clear();
        discussion.getNews().setDiscussion(null);
        discussion.setNews(null);
        session.delete(discussion);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Discussion> findAll(int firstResult, int maxResults) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<Discussion> query=session.createQuery("from Discussion ");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List<Discussion> discussionList=query.list();

        session.getTransaction().commit();
        session.close();
        return discussionList;
    }
}
