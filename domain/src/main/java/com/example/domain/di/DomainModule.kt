package com.example.domain.di

import com.example.domain.repository.SWAppRepository
import com.example.domain.usecase.GetCharacterByIdUseCase
import com.example.domain.usecase.GetPeopleUseCase
import org.koin.dsl.module

val domainModule = module {
    // Use Cases (логика приложения)
    factory { GetPeopleUseCase(get<SWAppRepository>()) }
    factory { GetCharacterByIdUseCase(get<SWAppRepository>()) }
}