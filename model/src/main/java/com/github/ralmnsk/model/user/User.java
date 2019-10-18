package com.github.ralmnsk.model.user;


import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date joinDate;

    @Column(name="role")
    private String role;

    @OneToMany (/*fetch = FetchType.LAZY, */cascade = CascadeType.ALL)
//    @JoinTable(name = "usr_nws",
//            joinColumns = { @JoinColumn(name = "usr_id") },
//            inverseJoinColumns =  @JoinColumn(name = "nws_id") )
    private List<News> newsList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "usr_msg",
            joinColumns = { @JoinColumn(name = "usr_id") },
            inverseJoinColumns =  @JoinColumn(name = "msg_id") )
    private List<Msg> msgList;

    public List<Msg> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Msg> msgList) {
        this.msgList = msgList;
    }

    public boolean addMsg(Msg msg) {
        if (msgList == null) {
            msgList = new ArrayList();
            msgList.add(msg);
            return true;
        } else {
            if (msgList.contains(msg)) {
                return false;
            }
        }
        msgList.add(msg);
        return true;
    }
//---------------------------------------------
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

    public User(Long id,String name, String password, Date date, String role) {
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

