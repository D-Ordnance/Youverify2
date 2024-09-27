package com.deeosoft.youverifytest2.feature.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deeosoft.youverifytest2.core.network.ServerProduct
import com.deeosoft.youverifytest2.feature.home.domain.usecase.ProductUseCase
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
): ViewModel() {
    private var _success: MutableLiveData<List<ServerProduct?>?> = MutableLiveData()
    val success: LiveData<List<ServerProduct?>?> = _success

    private var _failure: MutableLiveData<String> = MutableLiveData()
    val failure: LiveData<String> = _failure

    private var _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun getProduct(forceServer: Boolean = false){
        _loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.getProduct(forceServer).collect{
                if(it is Resource.Error){
                    _loading.postValue(false)
                    _failure.postValue(it.message!!)
                }
                if(it is Resource.Success){
                    _loading.postValue(false)
                    _success.postValue(it.data)
                }
            }
        }
    }
}