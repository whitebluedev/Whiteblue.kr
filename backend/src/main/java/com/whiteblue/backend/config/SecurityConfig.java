package com.whiteblue.backend.config;

import com.whiteblue.backend.domain.user.service.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final OAuthUserService oAuthUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .authorizeRequests(authorizeRequests -> {
                authorizeRequests.anyRequest()
                                 .permitAll();
            })
            .logout(logout -> {
                logout.invalidateHttpSession(true)
                      .deleteCookies("JSESSIONID")
                      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                      .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));
            })
            .oauth2Login(oAuth2LoginConfigurer -> {
                oAuth2LoginConfigurer.defaultSuccessUrl("http://localhost:3000")
                                     .userInfoEndpoint()
                                     .userService(oAuthUserService);
            });

        return http.build();
    }
}
