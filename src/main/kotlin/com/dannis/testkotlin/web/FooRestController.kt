package com.dannis.testkotlin.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by sam on 17-6-10.
 */
@RestController
@RequestMapping("/foo")
class FooRestController {

    @RequestMapping("hi")
    fun foo(): String {
        return "foo controller 你好!"
    }

    /*
        访问显示出错信息: /foo/divideByZero?d=0
     */
    @RequestMapping("divideByZero")
    fun devideByZero(@RequestParam d: Int): String {
//        var d = 0
        var a = 100 / d
        return "Exception! $a"
    }
}
