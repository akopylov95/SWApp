package com.example.swapplication.di

import com.example.domain.usecase.GetCharacterByIdUseCase
import com.example.domain.usecase.GetPeopleUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import swapplication.ui.viewmodel.CharacterDetailViewModel
import swapplication.ui.viewmodel.PeopleListViewModel

val appModule = module {
    // UseCases
    single { GetPeopleUseCase(get()) }
    single { GetCharacterByIdUseCase(get()) }

    // ViewModels
    viewModel { PeopleListViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }

    factory { GetPeopleUseCase(get()) }
    factory { GetCharacterByIdUseCase(get()) }
}