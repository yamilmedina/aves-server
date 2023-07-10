@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}
group = "com.wire"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.serializationJson)
    implementation(libs.ktor.serializationProtobuf)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.authjwt)
    implementation(libs.ktorSwaggerGenerator) {
        exclude("org.slf4j", "slf4j-api")
    }

    implementation(libs.koin)
    implementation(libs.koin.ktor)
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.postgresql)
    implementation(libs.hikaricp)
    implementation(libs.flyway.core)
    implementation(libs.arrow)

    implementation(libs.logbackClassic)

    testImplementation(libs.ktor.server.test)
    testImplementation(libs.kotlin.test.junit)
}

tasks {
    val jvmTargetVersion = JavaVersion.VERSION_17
    compileKotlin {
        kotlinOptions.jvmTarget = jvmTargetVersion.toString()
    }
}