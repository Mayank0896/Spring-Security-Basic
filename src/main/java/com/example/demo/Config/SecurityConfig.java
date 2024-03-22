package com.example.demo.Config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.Filters.ApiKeyFilter;

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

    // Deprecated verion.
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // return http.csrf().disable()
    // .authorizeHttpRequests()
    // .requestMatchers("/").permitAll()
    // .and()
    // .authorizeHttpRequests()
    // .requestMatchers("/unAuthGet").permitAll()
    // .and()
    // .authorizeHttpRequests().requestMatchers("/authGet").authenticated()
    // .and().formLogin().and().build();
    // }

    // @SuppressWarnings("deprecation") // using Lambda DSL
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     return http
    //             .authorizeRequests(auth -> {
    //                 auth.requestMatchers("/", "/unAuthGet").permitAll();
    //                 auth.requestMatchers("/authGet/**").authenticated();
    //                 auth.requestMatchers("/authFormGet/**").authenticated();
    //                 auth.anyRequest().authenticated();
    //             })
    //             .csrf(csrf -> csrf.disable())
    //             .sessionManagement(session -> session.sessionCreationPolicy(
    //                     SessionCreationPolicy.STATELESS))
    //             .httpBasic(it -> {
    //             })
    //             .addFilterBefore(new ApiKeyFilter(apiKey), UsernamePasswordAuthenticationFilter.class)
    //             // .formLogin(formLogin -> formLogin
    //             //         .loginPage("/login"))
    //             .build();
    // }

    @SuppressWarnings("deprecation") // using Lambda DSL
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> {
                    auth.requestMatchers("/authGet/**").authenticated();
                    auth.anyRequest().authenticated();
                })
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .httpBasic(it -> {
                })
                .addFilterBefore(new ApiKeyFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
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
