package com.wire.aves.server.infrastructure

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.Application
import io.ktor.server.application.install

fun Application.configureSwaggerApiDocs() {
    install(SwaggerUI) {
        swagger {
            swaggerUrl = "swagger-ui"
            forwardRoot = true
        }
        info {
            title = "Aves API"
            version = "latest"
            description = "Aves API for openAPI documentation."
        }
        server {
            url = "http://localhost:8080"
            description = "Development Server"
        }
    }
}