package com.github.ralmnsk.dto;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import javax.persistence.*;
import java.util.Date;


public class MsgDto {

    private Long id;

    private Date date;

    private String text;

    private NewsDto newsDto;

    private UserDto userDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public NewsDto getNewsDto() {
        return newsDto;
    }

    public void setNewsDto(NewsDto newsDto) {
        this.newsDto = newsDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public MsgDto(Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public MsgDto(){}

}
