package com.github.ralmnsk.service.security;

import com.github.ralmnsk.dao.user.UserRepository;
import com.github.ralmnsk.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Slf4j
@Service("userDS")
public class UserRepositoryUserDetailsService
        implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if (user != null) {

            return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),getRole(user));
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }

    private Collection<? extends GrantedAuthority> getRole(User user) {
        String userRole=user.getRole();
        return AuthorityUtils.createAuthorityList(userRole);
    }

}
