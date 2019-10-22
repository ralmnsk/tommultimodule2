package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.model.discussion.Discussion;

import java.util.List;

public interface DiscussionService {
    void create(Discussion discussion);
    Discussion read(Long id);
    void delete(Long id);
    List<Discussion> findAll(int firstResult, int maxResults);

}
