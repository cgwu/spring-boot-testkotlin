package com.dannis.testkotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//@SpringBootApplication
//class TestkotlinApplication


@RestController
@SpringBootApplication
class TestkotlinApplication {
    @RequestMapping("/")
    fun home(): String {
        return "<h1>你好, Spring Boot Kotlin!@@! JRebel自动重新加载，非常棒!!</h1>"
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(TestkotlinApplication::class.java, *args)
}
