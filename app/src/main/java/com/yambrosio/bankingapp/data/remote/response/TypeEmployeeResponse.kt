package com.yambrosio.bankingapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.yambrosio.bankingapp.domain.model.TypeEmployeeModel

data class TypeEmployeeResponse(
    @SerializedName("id_type_employee")
    val id: Long = 0,
    @SerializedName("name")
    val name: String
)

fun TypeEmployeeResponse.toTypeEmployee(): TypeEmployeeModel {
    return TypeEmployeeModel(
        id = id,
        name = name
    )
}