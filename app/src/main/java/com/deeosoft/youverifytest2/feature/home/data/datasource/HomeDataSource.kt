package com.deeosoft.youverifytest2.feature.home.data.datasource

import com.deeosoft.youverifytest2.core.network.ServerProduct
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource

interface HomeDataSource {
    suspend fun remoteSource(): Resource<List<ServerProduct?>>
    suspend fun localSource(): Resource<List<ServerProduct?>>
}