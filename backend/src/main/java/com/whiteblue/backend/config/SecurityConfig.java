package com.whiteblue.backend.config;

import com.whiteblue.backend.security.jwt.JWTAccessDeniedHandler;
import com.whiteblue.backend.security.jwt.JWTAuthenticationEntryPoint;
import com.whiteblue.backend.security.jwt.JWTAuthenticationFilter;
import com.whiteblue.backend.security.oAuth.OAuthAuthenticationSuccessHandler;
import com.whiteblue.backend.security.oAuth.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final OAuthUserService oAuthUserService;

    private final OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagementConfigurer ->
                                       sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.authorizeRequests(authorizeRequests ->
                                       authorizeRequests.antMatchers("/oAuth/**", "/auth/**")
                                                        .permitAll()
                                                        .anyRequest()
                                                        .authenticated()
        );

        http.formLogin(AbstractHttpConfigurer::disable)
            .oauth2Login(oAuth2LoginConfigurer -> {
                oAuth2LoginConfigurer.defaultSuccessUrl("http://localhost:3000")
                                     .authorizationEndpoint(authorizationEndpointConfig ->
                                                                    authorizationEndpointConfig.baseUri("/oAuth/login")
                                     )
                                     .redirectionEndpoint(redirectionEndpointConfig ->
                                                                  redirectionEndpointConfig.baseUri("/oAuth/redirect/**")
                                     )
                                     .userInfoEndpoint(userInfoEndpointConfig ->
                                                               userInfoEndpointConfig.userService(oAuthUserService))
                                     .successHandler(oAuthAuthenticationSuccessHandler);
            });
        http.exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer.authenticationEntryPoint(
                                                                                                 jwtAuthenticationEntryPoint)
                                                                                         .accessDeniedHandler(jwtAccessDeniedHandler));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
