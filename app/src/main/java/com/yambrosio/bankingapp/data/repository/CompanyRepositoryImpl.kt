package com.yambrosio.bankingapp.data.repository

import com.yambrosio.bankingapp.data.network.ApiService
import com.yambrosio.bankingapp.data.remote.response.toCompany
import com.yambrosio.bankingapp.domain.model.CompanyModel
import com.yambrosio.bankingapp.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val api: ApiService
) : CompanyRepository {

    override suspend fun getCompanies(): List<CompanyModel> {
        val listResponse = api.getCompanies("")
        return if (listResponse.isSuccessful) {
            val resultResponse = listResponse.body()
            resultResponse?.map { it.toCompany() } ?: emptyList()
        } else emptyList()
    }
}