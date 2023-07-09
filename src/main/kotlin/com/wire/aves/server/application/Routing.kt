package com.wire.aves.server.application

import com.wire.aves.server.application.apiversion.apiVersion
import com.wire.aves.server.application.login.login
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

/**
 * The Resources of the application, general routes config.
 *
 * todo: handle versioning, auth, error handling
 */
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        apiVersion()
        login()
    }
}
