package com.wire.aves.server.domain.user

import com.wire.aves.server.domain.id.UserId

internal interface UserRepository {

    fun insertUser(user: User)
    fun getPasswordByEmail(email: String): String
    fun getUserIdByEmail(email: String): UserId
}