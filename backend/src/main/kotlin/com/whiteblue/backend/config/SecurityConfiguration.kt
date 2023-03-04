package com.whiteblue.backend.config

import com.whiteblue.backend.security.jwt.JWTAccessDeniedHandler
import com.whiteblue.backend.security.jwt.JWTAuthenticationEntryPoint
import com.whiteblue.backend.security.jwt.JWTAuthenticationFilter
import com.whiteblue.backend.security.oAuth.OAuthAuthenticationSuccessHandler
import com.whiteblue.backend.security.oAuth.OAuthAuthorizationRequestRepository
import com.whiteblue.backend.security.oAuth.OAuthUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    private val oAuthUserService: OAuthUserService,

    private val oAuthAuthorizationRequestRepository: OAuthAuthorizationRequestRepository,

    private val oAuthAuthenticationSuccessHandler: OAuthAuthenticationSuccessHandler,

    private val jwtAuthenticationFilter: JWTAuthenticationFilter,

    private val jwtAuthenticationEntryPoint: JWTAuthenticationEntryPoint,

    private val jwtAccessDeniedHandler: JWTAccessDeniedHandler
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors()
            .and()
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http.authorizeHttpRequests {
            it.requestMatchers("/oAuth/**", "/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        }

        http.formLogin { it.disable() }
            .oauth2Login { oAuth2LoginConfigurer ->
                oAuth2LoginConfigurer
                    .authorizationEndpoint {
                        it.baseUri("/oAuth/login")
                            .authorizationRequestRepository(oAuthAuthorizationRequestRepository)
                    }
                    .redirectionEndpoint { it.baseUri("/oAuth/redirect/**") }
                    .userInfoEndpoint { it.userService(oAuthUserService) }
                    .successHandler(oAuthAuthenticationSuccessHandler)
            }

        http.exceptionHandling {
            it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        }

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}
