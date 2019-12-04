package com.github.ralmnsk.service.contact.creator;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.contact.ContactDto;
import com.github.ralmnsk.model.user.User;

public interface ContactCreator {
    ContactDto getContactDto(UserDto userDto, String mail);
    ContactDto delContactDto(UserDto userDto);
}
