package model.user;


import model.news.News;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


public class User implements Serializable {

    private Long id;
    private String name;
    private String pass;
    private Date joinDate;
    private String role;
    private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public boolean addNews(News news) {
        if (newsList == null) {
            newsList = new ArrayList();
            newsList.add(news);
            return true;
        } else {
            if (newsList.contains(news)) {
                return false;
            }
        }
        newsList.add(news);
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

    public User(String name, String password, Date date, String role) {
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

