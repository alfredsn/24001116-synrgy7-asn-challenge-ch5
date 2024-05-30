package com.example.triad.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.triad.repository.EmailData
import com.example.triad.repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    private val _sendEmailResult = MutableLiveData<Result<Any?>>()
    val sendEmailResult: LiveData<Result<Any?>> = _sendEmailResult

    fun sendEmail(emailData: EmailData) {
        repository.sendEmail(emailData).observeForever { result ->
            _sendEmailResult.postValue(result)
        }
    }
}