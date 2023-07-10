package com.wire.aves.server

import com.wire.aves.server.application.configureRouting
import com.wire.aves.server.infrastructure.configureContentNegotiation
import com.wire.aves.server.infrastructure.configureKoin
import com.wire.aves.server.infrastructure.configureSecurity
import com.wire.aves.server.infrastructure.configureSwaggerApiDocs
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

/**
 * TODO(ym)...
 * - add metrics + network logging interceptors with MDC.
 * - add flyway
 * - sec interceptor bearer token
 * - add websocket as app boundary
 */
@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureContentNegotiation()
    configureSwaggerApiDocs()
    configureSecurity()
    configureRouting()
}