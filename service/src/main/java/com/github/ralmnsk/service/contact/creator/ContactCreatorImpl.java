package com.github.ralmnsk.service.contact.creator;

import com.github.ralmnsk.dao.contact.ContactDao;
import com.github.ralmnsk.dao.contact.ContactDaoImpl;
import com.github.ralmnsk.dao.user.UserDaoHiberImpl;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactCreatorImpl implements ContactCreator {
    private HttpServletRequest req;

    public ContactCreatorImpl(HttpServletRequest req) {
        this.req = req;
    }

    @Override
    public void getContact() {
        HttpSession session=req.getSession();
        Contact contact=null;
        User user=(User)session.getAttribute("user");
        User readUser= UserDaoHiberImpl.getInstance().readUser(user);
        if(readUser.getContact()!=null){
            contact=readUser.getContact();
        }else{
            contact=new Contact("no");
        }
        session.setAttribute("contact",contact);
        String mail=req.getParameter("mail");

        if (mail!=null){
            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            if(mail.matches(regex)){
                    ContactDao contactDao= ContactDaoImpl.getInstance();
                if (readUser.getContact()==null){
                    contact.setMail(mail);
                    readUser.setContact(contact);
                    contact.setUser(readUser);
                    contactDao.create(contact);
                    session.setAttribute("contact",contact);
                } else {
                    Contact readContact=readUser.getContact();
                    readUser.getContact().setMail(mail);
                    contactDao.update(readContact.getId(),contact.getMail());
                    session.setAttribute("contact",readContact);
                }
            }
            session.setAttribute("user",readUser);
        }
    }

    @Override
    public void delContact(){
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        User readUser= UserDaoHiberImpl.getInstance().readUser(user);
        Contact contact=readUser.getContact();
        if(contact!=null){
            readUser.setContact(null);
            ContactDao contactDao=ContactDaoImpl.getInstance();
            contactDao.delete(contact.getId());
            Contact noContact=new Contact("no");
            session.setAttribute("contact",noContact);
        }
    }

}
