package com.dannis.testkotlin.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Created by sam on 17-6-13.
 */
@Service
class FooService {
    private val log = LoggerFactory.getLogger(FooService::class.java)

    fun doFoo() {
        log.info("FooService doFoo方法被调用")
    }
}