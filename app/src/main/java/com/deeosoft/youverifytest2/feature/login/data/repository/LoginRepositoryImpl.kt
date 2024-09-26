package com.deeosoft.youverifytest2.feature.login.data.repository

import android.util.Log
import com.deeosoft.youverifytest2.feature.login.data.datasource.LoginDataSource
import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.entity.LoginEntity
import com.deeosoft.youverifytest2.feature.login.domain.repository.LoginRepository
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val dataSource: LoginDataSource): LoginRepository {
    override suspend fun loginWithEmail(model: LoginEntity): Flow<Resource<OnboardingResponse>> =
        flow {
            val remoteResponse: Resource<OnboardingResponse>?
            remoteResponse = dataSource.loginWithEmail(model)
            emit(remoteResponse)
        }



}