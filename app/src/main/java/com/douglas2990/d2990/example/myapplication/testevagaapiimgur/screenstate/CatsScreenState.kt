package com.douglas2990.d2990.example.myapplication.testevagaapiimgur.screenstate

import androidx.annotation.StringRes
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.model.Data

sealed class CatsScreenState {
    data class Success(val data: List<Data>) : CatsScreenState()
    data class Error(@StringRes val messageId: Int): CatsScreenState()
    object Loading : CatsScreenState()
}