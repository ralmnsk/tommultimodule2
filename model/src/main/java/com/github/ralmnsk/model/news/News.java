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

    @Column(name="nws_name")
    private String nameNews;

    @Column(name="nws_data")
    private String dataNews;

    @Column(name="nws_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date dateNews;

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
