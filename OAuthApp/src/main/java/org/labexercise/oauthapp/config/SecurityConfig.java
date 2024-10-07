package org.labexercise.oauthapp.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.time.Duration;

@Configuration
class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(c-> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .authorizeHttpRequests(auth ->
                    auth
                            .requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated()
                            .anyRequest().authenticated()
            )
            .logout(l -> l.logoutSuccessUrl("/index.html").permitAll())
            .oauth2Login(Customizer.withDefaults());
        return http.build();
    }



}
