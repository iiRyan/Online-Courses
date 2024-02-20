package com.rayan.onlinecourses.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers("/").permitAll()
                                                .requestMatchers("/css/**", "img/**").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form.loginPage("/login")
                                                .loginProcessingUrl("/authenticationTheUser")
                                                .defaultSuccessUrl("/", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/"))
                                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

                // Redirect to the default page after logout

                return http.build();
        }

}
