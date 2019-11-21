package com.github.ralmnsk.service.deleter;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

public interface NewsDeleter {
    void delete();
    void setNews(News news);
    void setUser(User user);
}
