package com.github.ralmnsk.dto;



import com.github.ralmnsk.model.contact.ContactDto;

import com.github.ralmnsk.model.discussion.DiscussionDto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;






public class UserDto{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String pass;

    private Date joinDate;

    private String role;

    private Set<NewsDto> newsSetDto;

    private Set<DiscussionDto> discussionSetDto=new HashSet<>();

    private ContactDto contactDto;

    public ContactDto getContactDto() {
        return contactDto;
    }

    public void setContactDto(ContactDto contactDto) {
        this.contactDto = contactDto;
    }

    public Set<DiscussionDto> getDiscussionSetDto() {
        return discussionSetDto;
    }

    public void setDiscussionSetDto(Set<DiscussionDto> discussionSetDto) {
        this.discussionSetDto = discussionSetDto;
    }


    //---------------------------------------------
    public Set<NewsDto> getNewsSetDto() {
        return newsSetDto;
    }

    public void setNewsSetDto(Set<NewsDto> newsSetDto) {
        this.newsSetDto = newsSetDto;
    }

    public boolean addNewsDto(NewsDto newsDto) {
        if (newsSetDto == null) {
            newsSetDto = new HashSet<>();
            newsSetDto.add(newsDto);
            return true;
        } else {
            if (newsSetDto.contains(newsDto)) {
                return false;
            }
        }
        newsSetDto.add(newsDto);
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String password) {
        this.pass = password;
    }


    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date date) {
        this.joinDate = date;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDto(String name, String password, Date date, String role) {
        this.name = name;
        this.pass = password;
        this.joinDate = date;
        this.role = role;
    }

    public UserDto(Long id,String name, String password, Date date, String role) {
        this.id=id;
        this.name = name;
        this.pass = password;
        this.joinDate = date;
        this.role = role;
    }

    public UserDto() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", joinDate=" + joinDate +
                ", role='" + role + '\'' +
                '}';
    }

}

