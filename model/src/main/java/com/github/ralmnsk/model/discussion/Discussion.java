package com.github.ralmnsk.model.discussion;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = READ_WRITE)
@Table(name="disc")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="disc_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="nws_id")
    private News news;

    @ManyToMany(mappedBy = "discussionSet", cascade = CascadeType.ALL)
    private Set<User> userSet=new HashSet<>();

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> msgSet) {
        this.userSet = userSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Discussion(News news) {
        this.news = news;
    }

    public Discussion(Long id, News news) {
        this.id = id;
        this.news = news;
    }

    public Discussion() {
    }

}
