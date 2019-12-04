package com.github.ralmnsk.service.mapper;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.user.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class MapperImplTest {
    @Autowired
    private Mapper mapper;

    @Test
    void convert() {
        User user=new User(1L,"user","pass",new Date(),"ROLE_USER");
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        assertEquals(user.getName(),userDto.getName());
        assertEquals(user.getPass(),userDto.getPass());
        assertEquals(user.getJoinDate(),userDto.getJoinDate());
    }
}