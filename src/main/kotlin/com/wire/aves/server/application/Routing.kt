package com.wire.aves.server.application

import com.wire.aves.server.application.apiversion.apiVersion
import com.wire.aves.server.application.login.login
import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing

/**
 * The Resources of the application, general routes config.
 *
 * todo: handle versioning, auth, error handling
 */
fun Application.configureRouting() {
    routing {
        // no-auth routes
        apiVersion()
        login()

        // restricted routes
        authenticate {
            get("/restricted", {
                description = "Demo restricted api"
                securitySchemeName = "Auth"
                request { }
                response { }
            }) { call.respondText("Hello World, you are restricted!") }
        }
    }
}
