package com.github.ralmnsk.service.contact.creator;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.contact.ContactDto;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactCreatorImpl implements ContactCreator {
    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ModelMapper mapper;

    public ContactCreatorImpl() {
    }

    @Override
    public ContactDto getContactDto(UserDto userDto, String mail) {//user,  return contact

        ContactDto contactDto = null;

        UserDto readUserDto = userService.readUser(userDto);
        if (readUserDto.getContactDto() != null) {
            contactDto = readUserDto.getContactDto();
        } else {
            contactDto = new ContactDto("no");
        }

        if (mail != null) {
            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            if (mail.matches(regex)) {

                if (readUserDto.getContactDto() == null) {
                    contactDto.setMail(mail);
                    readUserDto.setContactDto(contactDto);
                    contactDto.setUserDto(readUserDto);
                    contactService.create(contactDto);

                } else {
                    ContactDto readContactDto = readUserDto.getContactDto();
                    readUserDto.getContactDto().setMail(mail);
                    contactService.update(readContactDto.getId(), contactDto.getMail());
                    contactDto = readContactDto;
                }
            }
        }
        return contactDto;
    }

    @Override
    public ContactDto delContactDto(UserDto userDto){
        UserDto readUserDto= userService.readUser(userDto);
        ContactDto contactDto=readUserDto.getContactDto();
        if(contactDto!=null){
            readUserDto.setContactDto(null);

            contactService.delete(contactDto.getId());
            ContactDto noContactDto=new ContactDto("no");
            contactDto=noContactDto;
        }
        return contactDto;
    }

}
