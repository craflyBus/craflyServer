package com.crafly.craflyserver.global.security;

import com.crafly.craflyserver.global.security.filter.ExceptionHandlerFilter
import com.crafly.craflyserver.global.security.filter.AuthAuthenticationFilter
import com.crafly.craflyserver.global.security.provider.CookieProvider
import com.crafly.craflyserver.global.security.provider.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val cookieProvider: CookieProvider,
    private val objectMapper: ObjectMapper,
    private val authenticationConfiguration: AuthenticationConfiguration,
) {
    @Bean
    fun filterChain(http: HttpSecurity, @Value("\${login.path}") loginUrl: String): SecurityFilterChain {
        http.httpBasic { it.disable() }
        http.formLogin { it.disable() }
        http.csrf { it.disable() }
        http.cors { it.disable() }
        http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        http.authorizeHttpRequests {
            it
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
        }
        .addFilterBefore(
            AuthAuthenticationFilter(
                jwtTokenProvider,
                cookieProvider,
                authenticationManager(),
                objectMapper,
                loginUrl
            ),
            UsernamePasswordAuthenticationFilter::class.java
        )
        .addFilterBefore(
            ExceptionHandlerFilter(),
            AuthAuthenticationFilter::class.java
        )
        return http.build()
    }

    @Bean
    fun authenticationManager(): AuthenticationManager =
        authenticationConfiguration.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
}
