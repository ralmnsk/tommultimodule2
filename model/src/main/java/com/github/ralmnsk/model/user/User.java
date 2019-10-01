package com.github.ralmnsk.model.user;


import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;


public class User implements Serializable {

    private Long id;
    private String name;
    private String pass;
    private Timestamp joinDate;
    private String role;
    private List<Long> newsList;

    public List<Long> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<Long> newsList) {
        this.newsList = newsList;
    }

    public boolean addNews(Long idUsrNews) {
        if (newsList == null) {
            newsList = new ArrayList();
            newsList.add(idUsrNews);
            return true;
        } else {
            if (newsList.contains(idUsrNews)) {
                return false;
            }
        }
        newsList.add(idUsrNews);
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

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp date) {
        this.joinDate = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String name, String password, Timestamp date, String role) {
        this.name = name;
        this.pass = password;
        this.joinDate = date;
        this.role = role;
    }

    public User(Long id,String name, String password, Timestamp date, String role) {
        this.id=id;
        this.name = name;
        this.pass = password;
        this.joinDate = date;
        this.role = role;
    }

    public User() {
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

