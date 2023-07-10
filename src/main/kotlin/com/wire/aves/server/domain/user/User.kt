package com.wire.aves.server.domain.user

import com.wire.aves.server.domain.id.UserId

data class User(
    val userId: UserId,
    val email: String,
    val phone: String,
    val name: String,
    val handle: String,
    val accent: Int,
    val managedBy: String = "wire",
)