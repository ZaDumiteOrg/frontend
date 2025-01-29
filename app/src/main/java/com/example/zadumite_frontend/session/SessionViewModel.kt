package com.example.zadumite_frontend.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SessionViewModel : ViewModel() {
    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> = _userId

    fun setUserId(id: Int) {
        _userId.value = id
    }
}