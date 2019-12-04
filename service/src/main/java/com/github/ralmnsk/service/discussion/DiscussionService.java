package com.github.ralmnsk.service.discussion;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.discussion.DiscussionDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface DiscussionService {
    boolean create(UserDto userDto, NewsDto newsDto);
    List<DiscussionDto> readByUser(UserDto userDto);
    void delete(Long id);
    List<DiscussionDto> findAll(int page, int size);
    Long count();
}
