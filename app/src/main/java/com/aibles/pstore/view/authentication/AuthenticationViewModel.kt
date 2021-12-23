package com.aibles.pstore.view.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibles.pstore.data.repository.AuthenticationRepository
import com.aibles.pstore.model.entities.Account
import com.aibles.pstore.model.entities.dto.AccountRegister
import com.aibles.pstore.model.entities.dto.BaseResponse
import com.aibles.pstore.utils.AppDispatchers
import com.aibles.pstore.utils.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor (
    private val repository: AuthenticationRepository,
    private val dispatchers: AppDispatchers
): ViewModel() {
    val loginStatus = MutableLiveData<Resource<Any?>>(Resource.loading())
    val registerStatus = MutableLiveData<Resource<BaseResponse>>(Resource.loading())
    val username = MutableLiveData("")
    val password = MutableLiveData("")

    fun login(account: Account){
        viewModelScope.launch(dispatchers.io){
            val response = repository.login(account)
            withContext(dispatchers.main) {
                loginStatus.value = response
            }
        }
    }

    fun signup(accountRegister: AccountRegister){
        viewModelScope.launch(dispatchers.io){
            val response = repository.signup(accountRegister)
            withContext(dispatchers.main){
                registerStatus.value = response
            }
        }
    }
}