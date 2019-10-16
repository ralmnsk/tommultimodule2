package com.github.ralmnsk.model.msg;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="msg")
public class Msg {
    @Id
    @GeneratedValue
    @Column(name="msg_id")
    private Long id;

    @Column(name="msg_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date date;

    @Column(name="msg_text")
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Msg(Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public Msg(){}

}