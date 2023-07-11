package com.wire.aves.server.infrastructure.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import com.wire.aves.server.infrastructure.logging.AppLoggers.infrastructureLogger
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.config.HoconApplicationConfig
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

fun Application.configureSecurity() {
    authentication {
        jwt {
            realm = JwtWrapper.realm
            verifier(JwtWrapper.verifier())
            validate { credential ->
                runCatching {
                    val zuid = UUID.fromString(credential.subject)
                    infrastructureLogger.debug("Validation success for user: {}", zuid)
                    JWTPrincipal(credential.payload)
                }.onFailure {
                    infrastructureLogger.error("Not valid user", it)
                }.getOrNull()
            }
        }
    }
}

object JwtWrapper {

    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val issuer: String = appConfig.property("jwt.issuer").getString()
    private val secret: String = appConfig.property("jwt.secret").getString()
    val realm: String = appConfig.property("jwt.realm").getString()

    @JvmStatic
    fun verifier(): JWTVerifier = JWT.require(Algorithm.HMAC512(secret))
        .withIssuer(issuer)
        .build()

    @JvmStatic
    fun generateToken(userId: UUID): String = JWT.create()
        .withSubject(userId.toString())
        .withIssuer(issuer)
        .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
        .sign(Algorithm.HMAC512(secret))
}
