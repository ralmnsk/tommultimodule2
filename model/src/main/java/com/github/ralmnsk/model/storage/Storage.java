package com.github.ralmnsk.model.storage;

public class Storage {
    private Long id;
    private Long usrId;
    private Long newsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Storage(Long id, Long usrId, Long newsId) {
        this.id = id;
        this.usrId = usrId;
        this.newsId = newsId;
    }

    public Storage(Long usrId, Long newsId) {
        this.usrId = usrId;
        this.newsId = newsId;
    }

    public Storage() {
    }
}
