package com.neds.cartrackmobilechallenge.infrastructure

import com.neds.cartrackmobilechallenge.data.enums.Status

data class RepositoryResult<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T? = null): RepositoryResult<T> =
            RepositoryResult(Status.SUCCESS, data, null)

        fun <T> error(msg: String, data: T? = null) = RepositoryResult(Status.ERROR, data, msg)
        fun <T> loading(data: T? = null) = RepositoryResult(Status.LOADING, data, null)
    }
}