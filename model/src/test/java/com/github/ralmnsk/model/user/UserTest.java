package com.github.ralmnsk.model.user;

import com.github.ralmnsk.model.news.News;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    private User testUser;
    private Date date;

    @Before
    public void setUp() throws Exception {
        date=new Date(new java.util.Date().getTime());
        testUser=new User(100L,"testUser", "testPass",date,"testRole");
    }

    @Test
    public void getNewsList() {
    }

    @Test
    public void setNewsList() {
    }

    @Test
    public void addNews() {
    }

    @Test
    public void getId() {
        long idUser=testUser.getId();
        assertEquals(100L,idUser);
    }

    @Test
    public void setId() {
        testUser.setId(333L);
        long id=testUser.getId();
        assertEquals(333L,id);
    }

    @Test
    public void getName() {
        String nameUser=testUser.getName();
        assertEquals("testUser", nameUser);
    }

    @Test
    public void setName() {
        testUser.setName("name333");
        String name=testUser.getName();
        assertEquals("name333",name);
    }

    @Test
    public void getPass() {
        String userPass=testUser.getPass();
        assertEquals("testPass", userPass);
    }

    @Test
    public void setPass() {
        testUser.setPass("pass333");
        String pass=testUser.getPass();
        assertEquals("pass333",pass);
    }

    @Test
    public void getJoinDate() {
        Date userDate=testUser.getJoinDate();
        assertEquals(date,userDate);
    }

    @Test
    public void setJoinDate() {
        String str="2015-03-31";
        Date date=Date.valueOf(str);
        testUser.setJoinDate(date);
        Date testDate=testUser.getJoinDate();
        System.out.println("date:"+date+" =?"+testDate);
        assertThat("2015-03-31",is(testDate.toString()));
    }

    @Test
    public void getRole() {
        String testRole=testUser.getRole();
        assertEquals("testRole", testRole);
    }

    @Test
    public void setRole() {
        testUser.setRole("testRole2");
        String role=testUser.getRole();
        assertEquals("testRole2",role);
    }

    @Test
    public void toString1() {
        System.out.println(testUser);
        String str="User{id=100, name='testUser', pass='testPass', joinDate=2019-09-25, role='testRole'}";
        assertThat(str, is(testUser.toString()));
    }
}