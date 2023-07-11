package com.wire.aves.server.infrastructure.config

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.HoconApplicationConfig
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {

    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val dbUrl = appConfig.property("db.jdbcUrl").getString()
    private val dbUser = appConfig.property("db.dbUser").getString()
    private val dbPassword = appConfig.property("db.dbPassword").getString()

    fun init() {
        Database.connect(createDataSource())
        val flyway = Flyway
            .configure()
            .dataSource(dbUrl, dbUser, dbPassword)
            .load()
        flyway.migrate()
    }

    private fun createDataSource(): HikariDataSource {
        val dataSourceConfig = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = dbUrl
            username = dbUser
            password = dbPassword
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(dataSourceConfig)
    }

//    suspend fun <T> dbQuery(block: () -> T): T =
//        withContext(Dispatchers.IO) {
//            transaction { block() }
//        }
}
