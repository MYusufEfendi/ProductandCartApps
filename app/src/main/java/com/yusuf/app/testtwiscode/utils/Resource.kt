package com.yusuf.app.testtwiscode.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T? = null): Resource<T>()
    data class Loading(val nothing: Nothing? = null): Resource<Nothing>()
    data class Failed(val status: String? = null, val message: String? = null): Resource<Nothing>()
    data class Exception(val exception: kotlin.Exception? = null): Resource<Nothing>()
}