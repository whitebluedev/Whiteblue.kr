package com.whiteblue.backend.config;

import com.whiteblue.backend.domain.user.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final OAuthUserService oAuthUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .authorizeRequests()
            .antMatchers("/board/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2Login()
            .defaultSuccessUrl("http://localhost:3000")
            .userInfoEndpoint()
            .userService(oAuthUserService);

        return http.build();
    }
}
