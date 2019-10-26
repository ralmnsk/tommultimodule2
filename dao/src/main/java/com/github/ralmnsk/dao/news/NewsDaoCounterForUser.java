package com.github.ralmnsk.dao.news;

import com.github.ralmnsk.model.user.User;

public interface NewsDaoCounterForUser {
    Integer getNewsCount(User user);
}
