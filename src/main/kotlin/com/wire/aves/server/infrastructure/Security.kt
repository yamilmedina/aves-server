package com.wire.aves.server.infrastructure

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.wire.aves.server.infrastructure.logging.AppLoggers.infrastructureLogger
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import java.util.*

fun Application.configureSecurity() {
    val jwtRealm = this.environment.config.property("jwt.realm").getString()
    val jwtIssuer = this.environment.config.property("jwt.issuer").getString()
    val jwtSecret = this.environment.config.property("jwt.secret").getString()
    JwtWrapper.init(jwtIssuer, jwtSecret)

    authentication {
        jwt {
            realm = jwtRealm
            verifier(JwtWrapper.verifier())
            validate { credential ->
                runCatching {
                    val zuid = UUID.fromString(credential.subject)
                    infrastructureLogger.debug("Validation success for user: {}", zuid)
                    JWTPrincipal(credential.payload)
                }.onFailure {
                    // todo(ym) response error?
                    infrastructureLogger.error("Not valid user", it)
                }.getOrNull()
            }
        }
    }
}

/**
 * fixme(ym) can/must be improved, this instance should be generated lazy on demand
 * or somehow get environments confs from yml to get issuer and secret.
 */
object JwtWrapper {
    private var issuer: String? = null
    private var secret: String? = null
    fun init(issuer: String, secret: String) {
        this.issuer = issuer
        this.secret = secret
    }

    @JvmStatic
    fun verifier(): JWTVerifier = JWT.require(Algorithm.HMAC512(secret))
        .withIssuer(issuer)
        .build()

    @JvmStatic
    fun generateToken(userId: UUID): String = JWT.create()
        .withSubject(userId.toString())
        .withIssuer(issuer)
        .withExpiresAt(getExpiration())
        .sign(Algorithm.HMAC512(secret))

    private fun getExpiration() = Date(System.currentTimeMillis() + 3600 * 24)

}