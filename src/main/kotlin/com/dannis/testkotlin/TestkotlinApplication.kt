package com.dannis.testkotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.Locale
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver


//@SpringBootApplication
//class TestkotlinApplication


@RestController
@SpringBootApplication
class TestkotlinApplication {
    @RequestMapping("/")
    fun home(): String {
        return "<h1>你好, Spring Boot Kotlin!@@! JRebel自动重新加载，非常棒!!</h1>"
    }


//    @Bean
//    fun localeResolver(): LocaleResolver {
//        val resolver = CookieLocaleResolver()     //使用cookie
//        resolver.cookieName = "lang"
//        resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE)        // 设置默认区域.
//        resolver.cookieMaxAge = 60 * 60 * 24        // 设置cookie有效期(seconds).
//        return resolver
//    }

//    @Bean
//    fun localeResolver(): LocaleResolver {
//        val resolver = SessionLocaleResolver()
//        resolver.setLocaleAttributeName("lang")
//        resolver.setDefaultLocale(Locale.CHINA)        // 设置默认区域.
//        return resolver
//    }

    @Bean
    fun localResolver(): LocaleResolver {
        val resolver = AcceptHeaderLocaleResolver()
        return resolver
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(TestkotlinApplication::class.java, *args)
}
