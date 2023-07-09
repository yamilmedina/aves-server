package com.wire.aves.server.domain.user

import com.wire.aves.server.domain.id.QualifiedId

data class User(
    val qualifiedId: QualifiedId,
    val email: String,
    val phone: String,
    val name: String,
    val handle: String,
    val accent: Int,
    val managedBy: String = "wire",
)