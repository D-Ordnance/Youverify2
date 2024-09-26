package com.deeosoft.youverifytest2.feature.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.entity.LoginEntity
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.deeosoft.youverifytest2.feature.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
): ViewModel() {

    private var _success: MutableLiveData<OnboardingResponse?> = MutableLiveData()
    val success: LiveData<OnboardingResponse?> = _success

    private var _failure: MutableLiveData<String> = MutableLiveData()
    val failure: LiveData<String> = _failure

    private var _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun login(entity: LoginEntity){
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.loginWithEmail(entity).collect{
                _loading.postValue(true)
                if(it is Resource.Error){
                    _failure.postValue("User does not exist")
                }
                if(it is Resource.Success){
                    _success.postValue(it.data)
                }
            }
        }
    }
}