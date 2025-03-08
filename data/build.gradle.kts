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

// Coroutines
val coroutines = "1.7.3"

// Serialization
val serialization = "1.6.0"

// Ktor Client
val ktor = "2.3.5"

//Koin
val koin = "4.0.1"

dependencies{
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines}")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${serialization}")

    // Ktor Client
    implementation("io.ktor:ktor-client-core:${ktor}")
    implementation("io.ktor:ktor-client-cio:${ktor}")
    implementation("io.ktor:ktor-client-serialization:${ktor}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktor}")
    implementation("io.ktor:ktor-client-content-negotiation:${ktor}")
    implementation("io.ktor:ktor-server-content-negotiation:${ktor}")

    //Koin
//    implementation("io.insert-koin:koin-android:${koin}")
//    implementation("io.insert-koin:koin-androidx-compose:${koin}")
    implementation("io.insert-koin:koin-core:${koin}")
    implementation(project(":domain"))
}