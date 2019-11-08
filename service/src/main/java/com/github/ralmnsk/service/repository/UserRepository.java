package com.github.ralmnsk.service.repository;

import com.github.ralmnsk.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
