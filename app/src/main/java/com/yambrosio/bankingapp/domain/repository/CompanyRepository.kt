package com.yambrosio.bankingapp.domain.repository

import com.yambrosio.bankingapp.domain.model.CompanyModel

interface CompanyRepository {
    suspend fun getCompanies(): List<CompanyModel>
}