package com.dannis.testkotlin.web

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

/**
 * Created by sam on 17-6-12.
 */
@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(value = Exception::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): ModelAndView {
        val mv = ModelAndView()
        mv.addObject("e", e)
        mv.addObject("uri", req.requestURI)
        mv.viewName = "error"
        return mv
    }
}