package com.crafly.craflyserver.global.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@RestController
@RequestMapping
annotation class WebAdapter(
    @get:AliasFor(annotation = RequestMapping::class, attribute = "path")
    val path: String = ""
)

