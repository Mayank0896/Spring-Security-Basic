package com.example.demo.Config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserInfoUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'loadUserByUsername'");
        // return new UserInfoUserDetails("user", "pass", List.of("admin, user"));
        String password = "pass";
        List<String> authorities = List.of("admin, user");
        String encodedPassword = passwordEncoder.encode(password);
        logger.info("Encoded password is : " + encodedPassword);
        System.out.println("Encoded password is : " + encodedPassword);
        return new UserInfoUserDetails(username, encodedPassword, authorities);
    }

}
