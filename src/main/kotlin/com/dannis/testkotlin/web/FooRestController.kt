package com.dannis.testkotlin.web

import org.springframework.web.bind.annotation.RequestMapping
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

}
