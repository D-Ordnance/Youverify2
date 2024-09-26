package com.deeosoft.youverifytest2.feature.login.domain.usecase

import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.entity.LoginEntity
import com.deeosoft.youverifytest2.feature.login.domain.repository.LoginRepository
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repo: LoginRepository) {
    suspend fun loginWithEmail(entity: LoginEntity): Flow<Resource<OnboardingResponse>> = repo.loginWithEmail(entity)
}