package com.github.ralmnsk.model.news;

import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


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

    @ManyToOne//(cascade = CascadeType.PERSIST)
    @JoinColumn(name="usr_id")
    private User user;

    @OneToOne (mappedBy = "news")
    private Discussion discussion;

    @OneToMany(mappedBy = "news",cascade = CascadeType.ALL)
    private Set<Msg> msgSet;

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public Set<Msg> getMsgSet() {
        return msgSet;
    }

    public void setMsgSet(Set<Msg> msgSet) {
        this.msgSet = msgSet;
    }

    public boolean addMsg(Msg msg) {
        if (msgSet == null) {
            msgSet = new HashSet<Msg>();
            msgSet.add(msg);
            return true;
        } else {
            if (msgSet.contains(msg)) {
                return false;
            }
        }
        msgSet.add(msg);
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
