package com.wire.aves.server.application.apiversion

import com.wire.aves.server.application.apiversion.VersionsResponse.Companion.currentVersion
import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.apiVersion() = route("/api-version") {
    get({
        description = "Get API Version"
        request { }
        response { HttpStatusCode.OK to { body<VersionsResponse> { } } }
    }) {
        call.respond(HttpStatusCode.OK, currentVersion)
    }
}

