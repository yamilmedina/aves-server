package com.wire.aves.server.application.apiversion

import kotlinx.serialization.Serializable

@Serializable
data class VersionsResponse(
    var development: Set<Int>,
    var domain: String = "aves.com",
    var federation: Boolean = false,
    var supported: Set<Int> = setOf(2),
) {

    fun latest() = "api/v${supported.max()}"

    companion object {
        @JvmStatic
        val currentVersion = VersionsResponse(
            development = setOf(2),
            supported = setOf(2),
        )
    }
}