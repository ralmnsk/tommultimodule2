package com.github.ralmnsk.dao.user;

import com.github.ralmnsk.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name = :name")
    User findByName(@Param("name") String name);
}
