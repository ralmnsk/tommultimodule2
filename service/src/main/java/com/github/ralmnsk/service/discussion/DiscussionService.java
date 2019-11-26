package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface DiscussionService {
    boolean create(User user, News news);
    List<Discussion> readByUser(User user);
    void delete(Long id);
    List<Discussion> findAll(int page, int size);

    Long count();
}
