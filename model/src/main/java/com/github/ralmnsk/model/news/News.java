package com.github.ralmnsk.model.news;

import java.io.Serializable;
import java.sql.Timestamp;

public class News implements Serializable {
    private Long idNews;
    private Long idUser;
    private String nameNews;
    private String dataNews;
    private Timestamp dateNews;

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

    public Timestamp getDateNews() {
        return dateNews;
    }

    public void setDateNews(Timestamp dateNews) {
        this.dateNews = dateNews;
    }

    public News() {
    }

    public News(long idNews, long idUser, String nameNews, String dataNews, Timestamp dateNews) {
        this.idNews = idNews;
        this.idUser = idUser;
        this.nameNews = nameNews;
        this.dataNews = dataNews;
        this.dateNews = dateNews;
    }

    public News(Long idUser, String nameNews, String dataNews, Timestamp dateNews) {
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
