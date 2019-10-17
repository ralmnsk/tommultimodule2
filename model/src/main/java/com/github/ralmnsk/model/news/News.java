package com.github.ralmnsk.model.news;

import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="nws")
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nws_id")
    private Long idNews;

    @Column(name="nws_name")
    private String nameNews;

    @Column(name="nws_data")
    private String dataNews;

    @Column(name="nws_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date dateNews;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
        //@JoinColumn(name = "usr_id", insertable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "nws_msg",
            joinColumns = { @JoinColumn(name = "nws_id") },
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public News() {
    }

    public News(long idNews, String nameNews, String dataNews, Date dateNews) {
        this.idNews = idNews;
        this.nameNews = nameNews;
        this.dataNews = dataNews;
        this.dateNews = dateNews;
    }

    public News(String nameNews, String dataNews, Date dateNews) {
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
