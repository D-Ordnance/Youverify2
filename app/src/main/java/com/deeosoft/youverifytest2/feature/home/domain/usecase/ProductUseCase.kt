package com.deeosoft.youverifytest2.feature.home.domain.usecase

import com.deeosoft.youverifytest2.core.network.ServerProduct
import com.deeosoft.youverifytest2.feature.home.domain.repository.HomeRepository
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class ProductUseCase(private val repo: HomeRepository) {
    suspend fun getProduct(forceServer: Boolean): Flow<Resource<List<ServerProduct?>>> = repo.getProduct(forceServer)
}