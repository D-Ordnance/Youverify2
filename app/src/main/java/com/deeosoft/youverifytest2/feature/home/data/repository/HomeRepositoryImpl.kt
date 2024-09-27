package com.deeosoft.youverifytest2.feature.home.data.repository

import com.deeosoft.youverifytest2.core.network.InternetConnectionService
import com.deeosoft.youverifytest2.core.network.ServerProduct
import com.deeosoft.youverifytest2.feature.home.data.datasource.HomeDataSource
import com.deeosoft.youverifytest2.feature.home.domain.repository.HomeRepository
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
): HomeRepository {
    override suspend fun getProduct(forceServer: Boolean): Flow<Resource<List<ServerProduct?>>> =
        flow {
            val response: Resource<List<ServerProduct?>>
            if (!forceServer) {
                response = dataSource.localSource()
                emit(response)
            }
            val remoteResponse: Resource<List<ServerProduct?>> = dataSource.remoteSource()
            emit(remoteResponse)

        }
}