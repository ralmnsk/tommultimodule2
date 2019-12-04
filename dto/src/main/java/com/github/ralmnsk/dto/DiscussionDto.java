package com.github.ralmnsk.model.discussion;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;


public class DiscussionDto {

    private Long id;

    private NewsDto newsDto;

    private Set<UserDto> userSetDto=new HashSet<>();

    public Set<UserDto> getUserSetDto() {
        return userSetDto;
    }

    public void setUserSetDto(Set<UserDto> userSetDto) {
        this.userSetDto = userSetDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NewsDto getNews() {
        return newsDto;
    }

    public void setNewsDto(NewsDto newsDto) {
        this.newsDto = newsDto;
    }

    public DiscussionDto(NewsDto newsDto) {
        this.newsDto = newsDto;
    }

    public DiscussionDto(Long id, NewsDto newsDto) {
        this.id = id;
        this.newsDto = newsDto;
    }

    public DiscussionDto() {
    }

}
