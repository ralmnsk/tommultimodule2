package com.github.ralmnsk.service.contact.creator;

import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactCreatorImpl implements ContactCreator {
    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;

    public ContactCreatorImpl() {
    }

    @Override
    public Contact getContact(User user, String mail) {//user,  return contact

        Contact contact = null;
        User readUser = userService.readUser(user);
        if (readUser.getContact() != null) {
            contact = readUser.getContact();
        } else {
            contact = new Contact("no");
        }

        if (mail != null) {
            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            if (mail.matches(regex)) {

                if (readUser.getContact() == null) {
                    contact.setMail(mail);
                    readUser.setContact(contact);
                    contact.setUser(readUser);
                    contactService.create(contact);

                } else {
                    Contact readContact = readUser.getContact();
                    readUser.getContact().setMail(mail);
                    contactService.update(readContact.getId(), contact.getMail());
                    contact = readContact;
                }
            }
        }
        return contact;
    }

    @Override
    public Contact delContact(User user){
        User readUser= userService.readUser(user);
        Contact contact=readUser.getContact();
        if(contact!=null){
            readUser.setContact(null);

            contactService.delete(contact.getId());
            Contact noContact=new Contact("no");
            contact=noContact;
        }
        return contact;
    }

}
