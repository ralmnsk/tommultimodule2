package com.github.ralmnsk.service.dispute;

import com.github.ralmnsk.dao.discussion.DiscussionDao;
import com.github.ralmnsk.dao.discussion.DiscussionDaoHiberImpl;
import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.user.User;
import java.util.*;

public class DisputeImpl implements Dispute {

    public DisputeImpl() {
    }

    @Override
    public List<Discussion> get(User user) {
        UserDao userDao= UserDaoHiberImpl.getInstance();
        User readUser=userDao.getById(user.getId());
        DiscussionDao discussionDao= DiscussionDaoHiberImpl.getInstance();
        List<Discussion> discussionList=discussionDao.readByUser(readUser);
        return discussionList;
    }
}
