package com.yambrosio.bankingapp.domain.repository

import com.yambrosio.bankingapp.domain.model.TypeEmployeeModel

interface TypeEmployeeRepository {
    suspend fun getTypeEmployees(): List<TypeEmployeeModel>
}