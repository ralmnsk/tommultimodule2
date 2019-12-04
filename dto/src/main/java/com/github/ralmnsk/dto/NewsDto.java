package com.github.ralmnsk.dto;

import com.github.ralmnsk.model.discussion.DiscussionDto;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;



public class NewsDto{

    private Long idNews;

    private String nameNews;

    private String dataNews;

    private Date dateNews;

    private UserDto userDto;

    private DiscussionDto discussionDto;

    private Set<MsgDto> msgSetDto;

    public DiscussionDto getDiscussionDto() {
        return discussionDto;
    }

    public void setDiscussionDto(DiscussionDto discussionDto) {
        this.discussionDto = discussionDto;
    }

    public Set<MsgDto> getMsgSetDto() {
        return msgSetDto;
    }

    public void setMsgSetDto(Set<MsgDto> msgSetDto) {
        this.msgSetDto = msgSetDto;
    }

    public boolean addMsgDto(MsgDto msgDto) {
        if (msgSetDto == null) {
            msgSetDto = new HashSet<MsgDto>();
            msgSetDto.add(msgDto);
            return true;
        } else {
            if (msgSetDto.contains(msgDto)) {
                return false;
            }
        }
        msgSetDto.add(msgDto);
        return true;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Long getIdNews() {
        return idNews;
    }

    public void setIdNews(Long idNews) {
        this.idNews = idNews;
    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public String getDataNews() {
        return dataNews;
    }

    public void setDataNews(String dataNews) {
        this.dataNews = dataNews;
    }

    public Date getDateNews() {
        return dateNews;
    }

    public void setDateNews(Date dateNews) {
        this.dateNews = dateNews;
    }

    public NewsDto() {
    }

    public NewsDto(long idNews, String nameNews, String dataNews, Date dateNews) {
        this.idNews = idNews;
        this.nameNews = nameNews;
        this.dataNews = dataNews;
        this.dateNews = dateNews;
    }

    public NewsDto(String nameNews, String dataNews, Date dateNews) {
        this.nameNews = nameNews;
        this.dataNews = dataNews;
        this.dateNews = dateNews;
    }

    @Override
    public String toString() {
        return "News{" +
                "idNews=" + idNews +
                ", nameNews='" + nameNews + '\'' +
                ", dataNews='" + dataNews + '\'' +
                ", dateNews=" + dateNews +
                '}';
    }


}
