package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.dao.discussion.DiscussionDao;
import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public class DiscussionServiceImpl implements DiscussionService {
    private DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();

    private static volatile DiscussionService instance;

    public static DiscussionService getInstance() {
        DiscussionService localInstance = instance;
        if (localInstance == null) {
            synchronized (DiscussionService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DiscussionServiceImpl();
                }
            }
        }
        return localInstance;
    }
    @Override
    public void create(User user, News news) {
        discussionDao.create(user, news);
    }

    @Override
    public Discussion read(Long id) {
        return discussionDao.read(id);
    }

    @Override
    public void delete(Long id) {
        discussionDao.delete(id);
    }

    @Override
    public List<Discussion> findAll(int firstResult, int maxResults) {
        return discussionDao.findAll(firstResult,maxResults);
    }
}
