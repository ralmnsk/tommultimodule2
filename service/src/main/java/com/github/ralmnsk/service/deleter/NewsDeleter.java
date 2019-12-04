package com.github.ralmnsk.service.deleter;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

public interface NewsDeleter {
    void delete();
    void setNewsDto(NewsDto newsDto);
    void setUserDto(UserDto userDto);
}
