package com.github.ralmnsk.service.dispute;

import com.github.ralmnsk.dto.UserDto;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.discussion.DiscussionDto;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface Dispute {
    List<DiscussionDto> get(UserDto userDto);
}
