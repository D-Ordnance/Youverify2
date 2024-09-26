package com.deeosoft.youverifytest2.feature.registration.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.deeosoft.youverifytest2.feature.registration.domain.entity.RegistrationEntity
import com.deeosoft.youverifytest2.feature.registration.domain.usecase.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
): ViewModel() {

    private var _success: MutableLiveData<OnboardingResponse?> = MutableLiveData()
    val success: LiveData<OnboardingResponse?> = _success

    private var _failure: MutableLiveData<String> = MutableLiveData()
    val failure: LiveData<String> = _failure

    private var _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun register(entity: RegistrationEntity){
        viewModelScope.launch(Dispatchers.IO) {
            registrationUseCase.register(entity).collect{
                _loading.postValue(true)
                if(it is Resource.Error){
                    _failure.postValue("Failed to register user")
                }
                if(it is Resource.Success){
                    _success.postValue(it.data)
                }
            }
        }
    }
}