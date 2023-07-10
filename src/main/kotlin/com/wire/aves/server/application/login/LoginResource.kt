package com.wire.aves.server.application.login

import com.wire.aves.server.application.apiversion.VersionsResponse
import com.wire.aves.server.domain.usecase.PerformLoginUseCase
import com.wire.aves.server.infrastructure.JwtWrapper
import com.wire.aves.server.infrastructure.logging.AppLoggers.applicationLogger
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.Cookie
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.login() = route("${VersionsResponse.currentVersion.latest()}/login") {
    val performLogin by inject<PerformLoginUseCase>()

    post({
        description = "Authenticate a user to obtain a cookie and first access token"
        request { body<SignInRequest>() }
        response { HttpStatusCode.Forbidden to { body<SignInResponse> { } } }
    }) {
        val loginRequest = call.receive<SignInRequest>()
        performLogin(loginRequest.email, loginRequest.password).fold({
            applicationLogger.error("Error while login in", it)
            call.respond(HttpStatusCode.InternalServerError)
        }) { userId ->
            val token = JwtWrapper.generateToken(userId.id)
            val result = SignInResponse(
                accessToken = token,
                expiresIn = getTokenExpirationInSeconds(),
                userId = userId.id.toString()
            )
            call.also { it.response.cookies.append(Cookie("zuid", token)) }.respond(HttpStatusCode.OK, result)
        }
    }
}

private fun getTokenExpirationInSeconds() = 3_600 * 24 // 1 day