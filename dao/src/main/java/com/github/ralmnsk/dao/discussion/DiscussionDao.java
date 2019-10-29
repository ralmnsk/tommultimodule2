package com.github.ralmnsk.dao.discussion;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface DiscussionDao {
    void create(User user, News news);
    List<Discussion> readByUser(User user);
    void delete(Long id);
    List<Discussion> findAll(int firstResult, int maxResults);

}
