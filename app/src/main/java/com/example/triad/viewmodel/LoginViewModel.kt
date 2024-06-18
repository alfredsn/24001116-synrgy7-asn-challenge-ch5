package com.example.triad.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triad.domain.usecase.LoginUseCase
import com.example.triad.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {

    fun validateLogin(
        context: Context,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            val result = loginUseCase.loginAction(context, username, password)
            if (result.isSuccess) {
                onSuccess()
            } else {
                onError()
            }
        }
    }

    fun logout(
        context: Context,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            val result = logoutUseCase.logoutAction(context)
            if (result.isSuccess) {
                onSuccess()
            } else {
                onError()
            }
        }
    }

}