package com.dannis.testkotlin.web

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by sam on 17-6-12.
 */
@Controller
class CommonController {
    private val log = LoggerFactory.getLogger(CommonController::class.java)

    @RequestMapping("/custom_error")
    fun error(): String {
        return "error"
    }
}