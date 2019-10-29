package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface DiscussionService {
    void create(User user, News news);
    Discussion read(Long id);
    void delete(Long id);
    List<Discussion> findAll(int firstResult, int maxResults);

}
