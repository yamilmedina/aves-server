@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}
group = "com.wire"
version = "0.0.1"
application {
    mainClass.set("com.wire.aves.server.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serializationJson)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.authjwt)

    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.sqliteJdbc)

    implementation(libs.logbackClassic)

    testImplementation(libs.ktor.server.test)
    testImplementation(libs.kotlin.test.junit)
}

tasks {
    val jvmTargetVersion = JavaVersion.VERSION_17
    compileKotlin {
        kotlinOptions.jvmTarget = jvmTargetVersion.toString()
    }
    jar {
        archiveFileName.set("aves.jar")
    }
}