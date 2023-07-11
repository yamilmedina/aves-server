package com.wire.aves.server.application.login

import io.ktor.server.auth.Principal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
) : Principal

