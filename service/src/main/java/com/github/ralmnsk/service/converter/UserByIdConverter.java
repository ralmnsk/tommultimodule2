package com.github.ralmnsk.service.converter;

import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserByIdConverter implements Converter<Long, User> {

    private UserRepository userRepo;

    @Autowired
    public UserByIdConverter(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User convert(Long id) {
        Optional<User> optionalIngredient = userRepo.findById(id);
        return optionalIngredient.isPresent() ?
                optionalIngredient.get() : null;
    }
}
