package com.yambrosio.bankingapp.core

import retrofit2.Response

object RetrofitHandleCall {
    fun <T> handleCallResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) response.body()
            ?.let { resultResponse -> return Resource.Success(resultResponse) }
        return Resource.Error(response.message())
    }
}