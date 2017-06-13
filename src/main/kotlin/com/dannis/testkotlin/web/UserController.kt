package com.dannis.testkotlin.web

import com.dannis.testkotlin.domain.UserEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.web.context.ContextLoader
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.servlet.ModelAndView
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


/**
 * Created by sam on 17-6-11.
 */
@Controller
@RequestMapping("/user")
class UserController : ApplicationContextAware {

    private var ctx: ApplicationContext? = null

    override fun setApplicationContext(ctx: ApplicationContext?) {
        this.ctx = ctx
    }

    private val log = LoggerFactory.getLogger(UserController::class.java)

    @RequestMapping("/index")
    fun index(request: HttpServletRequest, session: HttpSession): String {
//        log.info("UserEntity Controller index 方法被调用")       // test OK!
        session.setAttribute("sessionKey", "这是session里的值")
        request.setAttribute("requestKey", "这是request里的值")
        session.servletContext.setAttribute("servletCtxKey", "这是Servlet Context值")
        session.servletContext.setAttribute("now", Instant.now().atOffset(ZoneOffset.ofHours(8)))
        session.servletContext.setAttribute("nowLocal", LocalDateTime.now())

        var user = UserEntity("张三", "123")
        session.setAttribute("user", user)
        return "user/index"
    }

    @RequestMapping("/page-use-tpl")
    fun page_use_tpl(request: HttpServletRequest, session: HttpSession): String {
        session.setAttribute("sessionKey", "这是session里的值")
        request.setAttribute("requestKey", "这是request里的值")
        session.servletContext.setAttribute("servletCtxKey", "这是Servlet Context值")
        session.servletContext.setAttribute("now", Instant.now().atOffset(ZoneOffset.ofHours(8)))
        session.servletContext.setAttribute("nowLocal", LocalDateTime.now())

        var user = UserEntity("张三", "123")
        session.setAttribute("user", user)
        return "user/page-use-tpl"
    }

    @RequestMapping("/each-th-block")
    fun each_th_block(): ModelAndView {
        val users = listOf(
                UserEntity("张三", "100"),
                UserEntity("李四", "200"),
                UserEntity("王五", "300")
        )
        val mv = ModelAndView()
        mv.addObject("users", users)
        mv.addObject("msg","This\"s is <b>great很好!</b>")
        mv.viewName = "user/each-th-block"
        return mv
    }

}