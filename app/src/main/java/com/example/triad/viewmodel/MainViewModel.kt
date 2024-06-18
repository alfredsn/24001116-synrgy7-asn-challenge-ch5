package com.example.triad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.triad.domain.repository.EmailData
import com.example.triad.domain.repository.MainRepository

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _sendEmailResult = MutableLiveData<Result<Any?>>()
    val sendEmailResult: LiveData<Result<Any?>> = _sendEmailResult

    fun sendEmail(emailData: EmailData) {
        repository.sendEmail(emailData).observeForever { result ->
            _sendEmailResult.postValue(result)
        }
    }
}
