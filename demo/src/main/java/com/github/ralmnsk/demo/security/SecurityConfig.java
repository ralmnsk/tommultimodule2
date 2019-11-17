package com.github.ralmnsk.demo.security;

import com.github.ralmnsk.dao.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web
        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
        .configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
        .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
        .builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //for class SiteController @PreAuthorized
@ComponentScan({"com.github.ralmnsk.dao","com.github.ralmnsk.demo"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

//    @Autowired
//    public UserRepository userRepository;

    @Autowired
    public UserDetailsService userDetailsService;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserRepositoryUserDetailsService(userRepository);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
//                    .antMatchers("/site/**")
//                    .hasAnyRole("USER","ADMIN")
//
//                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/news","/goregistrate","/registration","/authorization","/login")
                    .access("permitAll")
//                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
//                    .loginProcessingUrl("/authorization")
//                    .defaultSuccessUrl("/welcome")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .csrf()
                    .disable()
                //.ignoringAntMatchers("/h2-console/**")

                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
        ;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder())
                ;

    }

}
