package model.news;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private long idNews;
    private long idUser;
    private String nameNews;
    private String dataNews;
    private Date dateNews;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdNews() {
        return idNews;
    }

    public void setIdNews(long idNews) {
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
}
