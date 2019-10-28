package com.github.ralmnsk.dao.discussion;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface DiscussionDao {
    void create(Discussion discussion);
    Discussion read(Long id);
    void delete(Long id);
    List<Discussion> findAll(int firstResult, int maxResults);

}
