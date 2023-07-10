package com.wire.aves.server.domain.id

import java.util.*

data class QualifiedId(val id: UUID, val domain: String) {
    override fun toString(): String {
        return "$id@$domain"
    }
}

typealias UserId = QualifiedId