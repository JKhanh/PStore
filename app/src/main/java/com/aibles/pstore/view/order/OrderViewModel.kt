package com.aibles.pstore.view.order

import androidx.lifecycle.*
import com.aibles.pstore.data.repository.AuthenticationRepository
import com.aibles.pstore.data.repository.OrderRepository
import com.aibles.pstore.model.entities.Profile
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import com.aibles.pstore.model.entities.dto.OrderResponse
import com.aibles.pstore.utils.AppDispatchers
import com.aibles.pstore.utils.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    authenticationRepository: AuthenticationRepository,
    private val orderRepository: OrderRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {
    val userProfile: LiveData<Resource<Profile>> = flow {
        emit(Resource.loading())
        val response = authenticationRepository.getProfile()
        if(response.isSuccessful()){
            val data = Resource.success(response.data!!.profile[0])
            emit(data)
        }
    }.asLiveData()

    var order: LiveData<Resource<OrderResponse>> = MutableLiveData()

    fun createOrder(items: List<ItemCartLocal>){
        viewModelScope.launch(dispatchers.main){
            orderRepository.createOrder(items.map {
                it.mapToRemote()
            })
        }
    }
}