package com.deeosoft.youverifytest2.feature.registration.data.datasource

import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.deeosoft.youverifytest2.feature.registration.domain.entity.RegistrationEntity

interface RegistrationDataSource {
    suspend fun register(model: RegistrationEntity): Resource<OnboardingResponse>
}