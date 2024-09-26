package com.deeosoft.youverifytest2.feature.registration.domain.usecase

import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.deeosoft.youverifytest2.feature.registration.domain.entity.RegistrationEntity
import com.deeosoft.youverifytest2.feature.registration.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.Flow

class RegistrationUseCase(private val repo: RegistrationRepository) {
    suspend fun register(entity: RegistrationEntity): Flow<Resource<OnboardingResponse>> = repo.loginWithEmail(entity)
}