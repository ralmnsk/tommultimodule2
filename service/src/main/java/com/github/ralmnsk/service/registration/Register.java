package com.github.ralmnsk.service.registration;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;

public interface Register {
    boolean isRegistered(UserDto userDto);
}
