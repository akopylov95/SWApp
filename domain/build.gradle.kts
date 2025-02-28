plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    kotlin("plugin.serialization")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    api(project(":data")) // TODO domain doesnt need to know anything about data, so we need to fix it with Koin/Dagger2 and move Repo Impl to domain
}
