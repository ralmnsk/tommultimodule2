package com.github.ralmnsk.model.news;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="nws")
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nws_id")
    private Long idNews;

    @Column(name="usr_id")
    private Long idUser;

    @Column(name="nws_name")
    private String nameNews;

    @Column(name="nws_data")
    private String dataNews;

    @Column(name="nws_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date dateNews;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public News(long idNews, long idUser, String nameNews, String dataNews, Date dateNews) {
        this.idNews = idNews;
        this.idUser = idUser;
        this.nameNews = nameNews;
        this.dataNews = dataNews;
        this.dateNews = dateNews;
    }

    public News(Long idUser, String nameNews, String dataNews, Date dateNews) {
        this.idUser = idUser;
        this.nameNews = nameNews;
        this.dataNews = dataNews;
        this.dateNews = dateNews;
    }

    @Override
    public String toString() {
        return "News{" +
                "idNews=" + idNews +
                ", idUser=" + idUser +
                ", nameNews='" + nameNews + '\'' +
                ", dataNews='" + dataNews + '\'' +
                ", dateNews=" + dateNews +
                '}';
    }
}
