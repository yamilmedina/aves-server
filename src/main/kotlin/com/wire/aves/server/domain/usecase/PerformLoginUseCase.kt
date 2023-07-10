package com.wire.aves.server.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import com.wire.aves.server.domain.id.QualifiedId
import com.wire.aves.server.domain.id.UserId
import com.wire.aves.server.domain.user.User
import com.wire.aves.server.domain.user.UserRepository
import java.util.*

/**
 * Allows to use perform a login, in other words validating credentials of the user.
 * If the password/hash is valid, it will return the [UserId].
 */
interface PerformLoginUseCase {
    operator fun invoke(email: String, password: String): Either<Exception, UserId>
}

internal class PerformLoginUseCaseImpl(private val userRepository: UserRepository) : PerformLoginUseCase {

    override fun invoke(email: String, password: String): Either<Exception, UserId> = either {
        when {
            email == "roman@aves.com" && password == "Qwerty123" -> inMemValidUser.userId
            else -> throw IllegalArgumentException("Invalid credentials")
        }
    }

    companion object {
        val inMemValidUser = User(
            userId = QualifiedId(UUID.fromString("71437131-ed9f-447f-be16-b88490323e6f"), "aves.com"),
            email = "roman@aves.com",
            handle = "roman.aves",
            phone = "49123123123",
            name = "roman bird",
            accent = 1
        )
    }
}
