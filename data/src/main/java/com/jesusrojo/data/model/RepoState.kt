package com.jesusrojo.data.model

sealed class RepoState<T>(val data: T? = null, val message: String? = null) {

//    class Success<T>(data: T) : RepoState<T>(data)
    class Success<T>(data: T, message: String?) : RepoState<T>(data, message)
    class Error<T>(message: String, data: T? = null) : RepoState<T>(data, message)
}