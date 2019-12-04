package com.github.ralmnsk.model.contact;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;

import javax.persistence.*;


public class ContactDto {

    private Long id;

    private String mail;

    private UserDto userDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserDto getUser() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ContactDto(String mail) {
        this.mail = mail;
    }

    public ContactDto(String mail, UserDto userDto) {
        this.mail = mail;
        this.userDto = userDto;
    }

    public ContactDto() {
    }
}
