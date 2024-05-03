package com.crafly.craflyserver.global.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import java.util.*


@Configuration
class MessageConfig {
    @Bean
    fun messageSource(): MessageSource {
        Locale.setDefault(Locale.US)
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setDefaultEncoding("UTF-8")

        return messageSource
    }
}