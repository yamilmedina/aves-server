package com.wire.aves.server.application.login

import io.ktor.server.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val email: String,
    val password: String
) : Principal

