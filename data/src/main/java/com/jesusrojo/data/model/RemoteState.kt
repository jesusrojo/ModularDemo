package com.jesusrojo.data.model

sealed class RemoteState<T>(val data: T? = null, val message: String? = null) {

//    class Success<T>(data: T) : RemoteState<T>(data)
    class Success<T>(data: T, message: String?) : RemoteState<T>(data, message)
    class Error<T>(message: String, data: T? = null) : RemoteState<T>(data, message)
}