package com.example.noteapp.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.presenter.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModelImpl : SplashViewModel, ViewModel(){
    override val openMainScreenLiveData = MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            delay(1000)
            openMainScreenLiveData.value = Unit
        }
    }
}