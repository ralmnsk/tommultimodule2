package com.github.ralmnsk.model.user;


import com.github.ralmnsk.model.news.News;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="usr")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usr_id")
    private Long id;

    @Column(name="usr_name")
    private String name;

    @Column(name="usr_pass")
    private String pass;

    @Column(name="usr_join_date")
    private Timestamp joinDate;

    @Column(name="role")
    private String role;

//    private List<News> newsList;


//    public List<News> getNewsList() {
//        return newsList;
//    }
//
//    public void setNewsList(List<News> newsList) {
//        this.newsList = newsList;
//    }
//
//    public boolean addNews(News news) {
//        if (newsList == null) {
//            newsList = new ArrayList();
//            newsList.add(news);
//            return true;
//        } else {
//            if (newsList.contains(news)) {
//                return false;
//            }
//        }
//        newsList.add(news);
//        return true;
//    }

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

