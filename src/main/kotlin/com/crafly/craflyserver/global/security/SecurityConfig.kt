package com.crafly.craflyserver.global.security;

import com.crafly.craflyserver.global.security.filter.ExceptionHandlerFilter
import com.crafly.craflyserver.global.security.filter.JwtAuthenticationFilter
import com.crafly.craflyserver.global.security.provider.CookieProvider
import com.crafly.craflyserver.global.security.provider.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val cookieProvider: CookieProvider,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic { it.disable() }
        http.csrf { it.disable() }
        http.cors { it.disable() }
        http.logout { it.disable() }
        http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        http.authorizeHttpRequests {
            it
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
        }
        .addFilterBefore(
            JwtAuthenticationFilter(jwtTokenProvider, cookieProvider),
            UsernamePasswordAuthenticationFilter::class.java
        )
        .addFilterBefore(
            ExceptionHandlerFilter(),
            JwtAuthenticationFilter::class.java
        )
        return http.build()
    }

    @Bean
    fun myPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}
