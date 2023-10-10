package com.example.demo.auth.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        User user = new User(
                "user",
                "$2a$10$EF0Op5auOpEB7t5nUX/iZOgZj1oDODXFn/gh0D73eOo/62uGxZRS2",
                List.of(userRole)
        );
        User admin = new User(
                "admin",
                "$2a$10$EF0Op5auOpEB7t5nUX/iZOgZj1oDODXFn/gh0D73eOo/62uGxZRS2",
                List.of(adminRole)
        );

        if (username.equals(user.getUsername())) {
            return user;
        }
        if (username.equals(admin.getUsername())) {
            return admin;
        }

        throw new UsernameNotFoundException("Username not found");
    }
}
