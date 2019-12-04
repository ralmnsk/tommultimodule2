package com.github.ralmnsk.service.pagination;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.List;
import java.util.Map;

public interface Paginator {
    Map<NewsDto, UserDto> viewNews(int page, int maxResults);
    Map<NewsDto, UserDto> viewNewsOfUser(int page,int maxResults, UserDto userDto);
    Long getAllNewsCount();
    int pagesCount(int allEntitiesCount, int maxEntitiesOnPage);
}
