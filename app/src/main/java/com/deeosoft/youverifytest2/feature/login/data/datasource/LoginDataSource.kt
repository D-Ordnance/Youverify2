package com.deeosoft.youverifytest2.feature.login.data.datasource

import com.deeosoft.youverifytest2.feature.login.domain.entity.LoginEntity
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    suspend fun loginWithEmail(model: LoginEntity): Resource<OnboardingResponse>
}

data class OnboardingResponse(
    val valid: Boolean,
    val success: Boolean,
    val message: String
)