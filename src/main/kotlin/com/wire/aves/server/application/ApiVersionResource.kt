package com.wire.aves.server.application

import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable

fun Route.apiVersion() = route("/api-version") {
    get({
        description = "Get API Version"
        request { }
        response { HttpStatusCode.OK to { body<Versions> { } } }
    }) {
        call.respond(HttpStatusCode.OK, Versions())
    }
}

@Serializable
data class Versions(
    var development: Set<Int> = setOf(2),
    var domain: String = "aves.com",
    var federation: Boolean = false,
    var supported: Set<Int> = setOf(2),
)