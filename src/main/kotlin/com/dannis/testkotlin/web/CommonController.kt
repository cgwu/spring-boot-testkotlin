package com.dannis.testkotlin.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by sam on 17-6-12.
 */
@Controller
class CommonController {

    @RequestMapping("/custom_error")
    fun error(): String {
        return "error"
    }
}