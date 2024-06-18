package com.example.triad.domain.usecase

import android.content.Context
import com.example.triad.domain.datastore.DataStoreManager

class LoginUseCase(private val dataStoreManager: DataStoreManager) {

    private val defaultUsername = "admin"
    private val defaultPassword = "admin123"

    suspend fun loginAction(
        context: Context,
        username: String,
        password: String
    ) : Result<Unit> {
        return if (username == defaultUsername && password == defaultPassword) {
            dataStoreManager.saveLoginStatus(context, true)
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid username or password"))
        }
    }
}