package com.github.ralmnsk.service.pagination;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.List;
import java.util.Map;

public interface Paginator {
    Map<News, User> viewNews(int page, int maxResults);
    Map<News, User> viewNewsOfUser(int page,int maxResults, User user);
    Long getAllNewsCount();
    int pagesCount(int allEntitiesCount, int maxEntitiesOnPage);
}
