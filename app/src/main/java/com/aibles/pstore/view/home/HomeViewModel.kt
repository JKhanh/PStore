package com.aibles.pstore.view.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.aibles.pstore.data.repository.ProductRepository
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.model.entities.dto.ProductResponse
import com.aibles.pstore.utils.AppDispatchers
import com.aibles.pstore.utils.remote.NetworkException
import com.aibles.pstore.utils.remote.Resource
import com.aibles.pstore.utils.remote.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val dispatcher: AppDispatchers
): ViewModel() {
    private var _products: Flow<Resource<List<Product>>> = flow {
        emit(Resource.loading())
        val response = repository.getAllProduct()
        if (response.isSuccessful()){
            emit(Resource.success(response.data!!.results))
        } else {
            emit(Resource.error(response.error ?: NetworkException()))
        }
    }
    val products: LiveData<Resource<List<Product>>> get() = _products.asLiveData()

    val secProduct = Stack<Product>()

    fun getProducts(){
        _products = flow {
            emit(Resource.loading())
            val response = repository.getAllProduct()
            if (response.isSuccessful()){
                emit(Resource.success(response.data!!.results))
            } else {
                emit(Resource.error(response.error ?: NetworkException()))
            }
        }
    }

    private var _productRec: LiveData<Resource<List<Product>>> = MutableLiveData()
    val productRec: LiveData<Resource<List<Product>>> get() = _productRec

    fun getProductRecommend() {
        val prodFlow = flow {
            emit(Resource.loading())
            val response = repository.getRecommend(secProduct.peek().id)
            if (response.isSuccessful()){
                emit(Resource.success(response.data!!))
            }
        }
            .asLiveData()
        _productRec = prodFlow
    }
}