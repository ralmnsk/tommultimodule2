package com.github.ralmnsk.model.user;


import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;


@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = READ_WRITE)
@Table(name="usr")

public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usr_id")
    private Long id;

    @Column(name="usr_name")
    private String name;

    @Column(name="usr_pass")
    private String pass;

    @Column(name="usr_join_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date joinDate;

    @Column(name="role")
    private String role;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<News> newsSet;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usr_disc",
            joinColumns = @JoinColumn(name = "usr_id"),
            inverseJoinColumns = @JoinColumn(name = "disc_id")
    )
    private Set<Discussion> discussionSet=new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<Discussion> getDiscussionSet() {
        return discussionSet;
    }

    public void setDiscussionSet(Set<Discussion> discussionSet) {
        this.discussionSet = discussionSet;
    }


    //---------------------------------------------
    public Set<News> getNewsSet() {
        return newsSet;
    }

    public void setNewsSet(Set<News> newsSet) {
        this.newsSet = newsSet;
    }

    public boolean addNews(News news) {
        if (newsSet == null) {
            newsSet = new HashSet<>();
            newsSet.add(news);
            return true;
        } else {
            if (newsSet.contains(news)) {
                return false;
            }
        }
        newsSet.add(news);
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String password) {
        this.pass = password;
    }


    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date date) {
        this.joinDate = date;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String name, String password, Date date, String role) {
        this.name = name;
        this.pass = password;
        this.joinDate = date;
        this.role = role;
    }

    public User(Long id,String name, String password, Date date, String role) {
        this.id=id;
        this.name = name;
        this.pass = password;
        this.joinDate = date;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", joinDate=" + joinDate +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("usr"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return getPass();
    }

    @Override
    public String getUsername() {
        return getName();
    }
}

