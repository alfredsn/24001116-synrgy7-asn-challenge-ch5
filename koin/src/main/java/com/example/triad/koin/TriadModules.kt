package com.example.triad.koin

import com.example.triad.data.repository.MainRepositoryImpl
import com.example.triad.domain.datastore.DataStoreManager
import com.example.triad.factory.ViewModelFactory
import com.example.triad.domain.repository.MainRepository
import com.example.triad.domain.usecase.LoginUseCase
import com.example.triad.domain.usecase.LogoutUseCase
import com.example.triad.viewmodel.LoginViewModel
import com.example.triad.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val triadModules = module {
    single { DataStoreManager }

    single { LoginUseCase(get()) }
    single { LogoutUseCase(get()) }

    factory { ViewModelFactory(get(), get()) }

    viewModel { LoginViewModel(get(), get()) }
    viewModel { MainViewModel(get()) }

    single<MainRepository> { MainRepositoryImpl() }


}
