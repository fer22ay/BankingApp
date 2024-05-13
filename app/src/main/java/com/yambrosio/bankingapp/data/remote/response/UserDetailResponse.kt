package com.yambrosio.bankingapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.yambrosio.bankingapp.domain.model.UserDetailModel

data class UserDetailResponse(
    @SerializedName("id_user")
    val id: Long = 0
)

fun UserDetailResponse.toUserDetail(): UserDetailModel {
    return UserDetailModel(id = id)
}