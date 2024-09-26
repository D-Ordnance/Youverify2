package com.deeosoft.youverifytest2.feature.registration.domain.repository

import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.deeosoft.youverifytest2.feature.registration.domain.entity.RegistrationEntity
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {
    suspend fun loginWithEmail(model: RegistrationEntity): Flow<Resource<OnboardingResponse>>
}