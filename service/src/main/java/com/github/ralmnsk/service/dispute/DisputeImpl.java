package com.github.ralmnsk.service.dispute;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DisputeImpl implements Dispute {

    @Autowired
    private UserService userService;
    @Autowired
    private DiscussionService discussionService;

    public DisputeImpl() {
    }

    @Override
    public List<Discussion> get(User user) {
        List<Discussion> discussionList=discussionService.readByUser(user);
        return discussionList;
    }
}
