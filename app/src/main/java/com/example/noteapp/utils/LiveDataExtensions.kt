package com.example.noteapp.utils

import androidx.lifecycle.MutableLiveData

fun eventLiveData() = MutableLiveData<Unit>()

fun <T> eventValueLiveData() = MutableLiveData<T>()