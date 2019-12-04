package com.github.ralmnsk.service.dispute;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.discussion.DiscussionDto;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DisputeImpl implements Dispute {

    @Autowired
    private UserService userService;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private ModelMapper mapper;

    public DisputeImpl() {
    }

    @Override
    public List<DiscussionDto> get(UserDto userDto) {
        List<DiscussionDto> discussionListDto=discussionService.readByUser(userDto);

        return discussionListDto;
    }
}
