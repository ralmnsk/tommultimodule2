package com.github.ralmnsk.model.contact;

import com.github.ralmnsk.model.user.User;

import javax.persistence.*;

@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_id")
    private Long id;
    @Column (name="c_mail")
    private String mail;

    @OneToOne
    @JoinColumn(name="usr_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact(String mail) {
        this.mail = mail;
    }

    public Contact(String mail, User user) {
        this.mail = mail;
        this.user = user;
    }

    public Contact() {
    }
}
