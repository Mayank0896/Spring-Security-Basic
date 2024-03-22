package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // To use @Bean annotation inside class SecurityConfig.
@EnableWebSecurity(debug = true)
// - **`@EnableWebSecurity(debug = true)`:** Add `debug=true` to the
// `@EnableWebSecurity` annotation. This logs detailed information about the
// auto-configuration process, including which parts are getting disabled due to
// your custom configuration. This can help pinpoint any unexpected behavior.
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/unAuthGet").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/authGet").authenticated()
                .and().formLogin().and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
