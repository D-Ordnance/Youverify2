package com.deeosoft.youverifytest2.feature.registration.data.repository

import com.deeosoft.youverifytest2.core.network.InternetConnectionService
import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.deeosoft.youverifytest2.feature.registration.data.datasource.RegistrationDataSource
import com.deeosoft.youverifytest2.feature.registration.domain.entity.RegistrationEntity
import com.deeosoft.youverifytest2.feature.registration.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(private val internetService: InternetConnectionService, private val dataSource: RegistrationDataSource): RegistrationRepository {
    override suspend fun register(model: RegistrationEntity): Flow<Resource<OnboardingResponse>> =
        flow {
            if(internetService.hasInternetConnection()){
                val remoteResponse: Resource<OnboardingResponse>?
                remoteResponse = dataSource.register(model)
                emit(remoteResponse)
            }else{
                emit(Resource.Error<OnboardingResponse>("No Internet Connection"))
            }

        }
}