package com.whiteblue.backend.config;

import com.whiteblue.backend.security.OAuth.OAuthAuthenticationFailureHandler;
import com.whiteblue.backend.security.OAuth.OAuthAuthenticationSuccessHandler;
import com.whiteblue.backend.security.OAuth.OAuthUserService;
import com.whiteblue.backend.security.util.CookieAuthorizationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final OAuthUserService oAuthUserService;

    private final OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    private final OAuthAuthenticationFailureHandler oAuthAuthenticationFailureHandler;

    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagementConfigurer -> {
                sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            });

        http.authorizeRequests(authorizeRequests -> {
            authorizeRequests.antMatchers("/oAuth/**")
                             .permitAll()
                             .anyRequest()
                             .authenticated();
        });

        http.formLogin(AbstractHttpConfigurer::disable)
            .oauth2Login(oAuth2LoginConfigurer -> {
                oAuth2LoginConfigurer.defaultSuccessUrl("http://localhost:3000")
                                     .authorizationEndpoint(authorizationEndpointConfig -> {
                                         authorizationEndpointConfig.baseUri("/oAuth/login")
                                                                    .authorizationRequestRepository(cookieAuthorizationRequestRepository);
                                     })
                                     .redirectionEndpoint(redirectionEndpointConfig -> {
                                         redirectionEndpointConfig.baseUri("/oAuth/redirect");
                                     })
                                     .userInfoEndpoint(userInfoEndpointConfig -> {
                                         userInfoEndpointConfig.userService(oAuthUserService);
                                     })
                                     .successHandler(oAuthAuthenticationSuccessHandler)
                                     .failureHandler(oAuthAuthenticationFailureHandler);
            });

        return http.build();
    }
}
