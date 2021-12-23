package com.aibles.pstore.view.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aibles.pstore.data.repository.AuthenticationRepository
import com.aibles.pstore.model.entities.Profile
import com.aibles.pstore.utils.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    authenticationRepository: AuthenticationRepository
) : ViewModel() {
    val userProfile: LiveData<Resource<Profile>> = flow {
        emit(Resource.loading())
        val response = authenticationRepository.getProfile()
        if(response.isSuccessful()){
            val data = Resource.success(response.data!!.profile[0])
            emit(data)
        }
    }.asLiveData()
}