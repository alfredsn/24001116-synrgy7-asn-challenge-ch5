package com.example.triad.domain.repository

import androidx.lifecycle.LiveData

interface MainRepository {
    fun sendEmail(emailData: EmailData): LiveData<Result<Any?>>
}
