package com.wire.aves.server.application.login

import com.wire.aves.server.application.apiversion.VersionsResponse
import com.wire.aves.server.domain.usecase.PerformLoginUseCase
import com.wire.aves.server.infrastructure.logging.AppLoggers.applicationLogger
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.login() = route("${VersionsResponse.currentVersion.latest()}/user") {
    val performLogin by inject<PerformLoginUseCase>()

    post({
        description = "Authenticate a user to obtain a cookie and first access token"
        request { body<SignInRequest>() }
        response { HttpStatusCode.Forbidden to { body<String> { } } }
    }) {
        val loginRequest = call.receive<SignInRequest>()
        performLogin(loginRequest.email, loginRequest.password).fold({
            applicationLogger.error("Error while login in", it)
            call.respond(HttpStatusCode.InternalServerError)
        }) {
            // later raise exception from domain
            applicationLogger.debug("Is Login valid for user[${loginRequest.email}] -> $it")
            call.respond(HttpStatusCode.OK)
        }
        // build jwt with user id
        // generate access token
        // respoind with access token + cookie
    }
}