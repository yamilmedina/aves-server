package com.wire.aves.server.infrastructure.config

import com.wire.aves.server.domain.usecase.PerformLoginUseCase
import com.wire.aves.server.domain.usecase.PerformLoginUseCaseImpl
import com.wire.aves.server.domain.user.UserRepository
import com.wire.aves.server.infrastructure.repository.UserRepositoryImpl
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

val appModule = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::PerformLoginUseCaseImpl) { bind<PerformLoginUseCase>() }
}

fun Application.configureKoin() {
    install(Koin) {
        modules(appModule)
    }
}
