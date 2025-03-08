package com.example.data.di

import com.example.data.repositoryImpl.ApiService
import com.example.data.repositoryImpl.SWAppRepositoryImpl
import com.example.domain.repository.SWAppRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module{
    // Сетевой клиент Ktor
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    // API сервис
    single { ApiService(get()) }

    // Реализация репозитория
    single<SWAppRepository> { SWAppRepositoryImpl(get()) }
}
