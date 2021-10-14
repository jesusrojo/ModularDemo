package com.jesusrojo.list.model

sealed class UiState<T>(val data: T? = null, val message: String? = null) {

   //todo class SuccessAndMsg<T>(data: T, message: String? = null) : UiState<T>(data)
    class Success<T>(data: T) : UiState<T>(data)
    class Loading<T>(data: T? = null) : UiState<T>(data)
    class Error<T>(message: String, data: T? = null) : UiState<T>(data, message)
}