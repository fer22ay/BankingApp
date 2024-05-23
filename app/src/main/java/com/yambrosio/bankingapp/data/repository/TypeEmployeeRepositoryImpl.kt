package com.yambrosio.bankingapp.data.repository

import com.yambrosio.bankingapp.data.network.ApiService
import com.yambrosio.bankingapp.domain.model.TypeEmployeeModel
import com.yambrosio.bankingapp.domain.repository.TypeEmployeeRepository
import javax.inject.Inject

class TypeEmployeeRepositoryImpl @Inject constructor(
    private val api: ApiService
) : TypeEmployeeRepository {

    override suspend fun getTypeEmployees(): List<TypeEmployeeModel> {
        TODO("Not yet implemented")
    }
}