package com.wire.aves.server.infrastructure.repository

import com.wire.aves.server.domain.id.UserId
import com.wire.aves.server.domain.user.User
import com.wire.aves.server.domain.user.UserRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImpl : UserRepository {
    override fun insertUser(user: User) {
        transaction {
            addLogger(StdOutSqlLogger) // todo(poc) move this out to a wrapper ext function to enable log by param
            UsersEntity.insertIgnore {
                it[userId] = user.userId.id
                it[name] = user.name
                it[email] = user.email
                it[handle] = user.handle
                it[phone] = user.phone
                it[accent] = user.accent
                it[password] = user.password
                it[complete] = ""
                it[preview] = ""
            }
            commit()
        }
    }

    // todo(poc): change this to hash comparison using bcrypt instead of plain text
    override fun getPasswordByEmail(email: String): String = transaction {
        addLogger(StdOutSqlLogger)
        UsersEntity.select { UsersEntity.email eq email }.first()[UsersEntity.password]
    }

    override fun getUserIdByEmail(email: String): UserId = transaction {
        addLogger(StdOutSqlLogger)
        UserId(UsersEntity.select { UsersEntity.email eq email }.first()[UsersEntity.userId], "aves.com")
    }
}

/**
 * Represents the table of users.
 * This can live in a separate file, but for the sake of simplicity it is here, just code for the PoC.
 */
object UsersEntity : Table("Users") {
    val userId = uuid("user_id")

    //    val domain = varchar("name", 255) // todo: add domain column
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val handle = varchar("handle", 255)
    val password = varchar("password", 255)
    val phone = varchar("phone", 255)
    val accent = integer("accent").default(0)
    val complete = varchar("complete", 255)
    val preview = varchar("preview", 255)
    val passwordReset = bool("password_reset").default(false)

    override val primaryKey = PrimaryKey(userId, name = "PK_userId")
}

