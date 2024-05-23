package com.yambrosio.bankingapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.yambrosio.bankingapp.domain.model.CompanyModel

data class CompanyResponse(
    @SerializedName("id_company")
    val id: Long = 0,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone")
    val phone: String,
)

fun CompanyResponse.toCompany(): CompanyModel {
    return CompanyModel(
        id = id,
        name = name,
        address = address,
        phone = phone
    )
}