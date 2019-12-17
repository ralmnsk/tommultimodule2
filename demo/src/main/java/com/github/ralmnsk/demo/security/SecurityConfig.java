package com.github.ralmnsk.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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

@Slf4j
@Configuration
@EnableWebSecurity
//@Profile("deploy")
//@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true) //for class SiteController @PreAuthorized
//@ComponentScan({"com.github.ralmnsk.demo"}) //"com.github.ralmnsk.dao",
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDS")
    public UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/","/news","/login","/goregistrate","/registration")
                    .permitAll()

                    .antMatchers("/site/*","/*","site/inform")
                    .hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                    .antMatchers("/site/inform/admin","/site/inform/admin/*","/site/inform/admin/**")
                    .hasAnyAuthority("ROLE_ADMIN")
                .and()
                    .exceptionHandling().accessDeniedPage("/")

                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .defaultSuccessUrl("/welcome")
                    .failureUrl("/login?error=true")
                    .permitAll()

                .and()
                    .logout()
                    .logoutSuccessUrl("/site/logout")
                    .invalidateHttpSession(true)
                    .permitAll()

                .and()
                    .csrf()
                    .disable();
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
                .passwordEncoder(encoder());

    }

}
