package com.wire.aves.server

import com.wire.aves.server.application.configureRouting
import com.wire.aves.server.infrastructure.configureContentNegotiation
import com.wire.aves.server.infrastructure.configureKoin
import com.wire.aves.server.infrastructure.configureSecurity
import com.wire.aves.server.infrastructure.configureSwaggerApiDocs
import io.ktor.server.application.Application
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val environment = applicationEngineEnvironment {
        developmentMode = true
        connector { port = 8080 }
        module(Application::module)
    }
    embeddedServer(Netty, environment).start(wait = true)
}

/**
 * TODO(ym)...
 * - add metrics + network logging interceptors with MDC.
 * - add flyway
 * - sec interceptor bearer token
 * - add websocket as app boundary
 */
fun Application.module() {
    configureKoin()
    configureContentNegotiation()
    configureSwaggerApiDocs()
    configureSecurity()
    configureRouting()
}