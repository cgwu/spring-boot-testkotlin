package com.dannis.testkotlin.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.slf4j.LoggerFactory

/**
 * Created by sam on 17-6-11.
 */
@Controller
@RequestMapping("/user")
class UserController {

    private val log = LoggerFactory.getLogger(UserController::class.java)

    @RequestMapping("/index")
    fun index(): String {
//        log.info("User Controller index 方法被调用")       // test OK!
        return "user/index"
    }


}