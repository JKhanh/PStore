package com.aibles.pstore.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aibles.pstore.data.repository.ProductRepository
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.dto.ProductResponse
import com.aibles.pstore.utils.AppDispatchers
import com.aibles.pstore.utils.remote.Resource
import com.aibles.pstore.utils.remote.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val dispatcher: AppDispatchers
): ViewModel() {
    private var _products: LiveData<Resource<ProductResponse>> = flow {
        emit(Resource.loading())
        val response = repository.getAllProduct()
        if (response.isSuccessful()) {
            emit(Resource.success(response.data!!))
        }
    }
        .asLiveData()
    val products: LiveData<Resource<ProductResponse>> get() = _products
}