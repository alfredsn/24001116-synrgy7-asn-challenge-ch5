package com.example.triad.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.triad.domain.repository.MainRepository
import com.example.triad.domain.usecase.LoginUseCase
import com.example.triad.domain.usecase.LogoutUseCase
import com.example.triad.viewmodel.LoginViewModel
import com.example.triad.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val mainRepository: MainRepository,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(mainRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(loginUseCase, logoutUseCase) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
