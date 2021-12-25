package com.aibles.pstore.view.search

import androidx.lifecycle.ViewModel
import com.aibles.pstore.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryImpl: ProductRepository
) : ViewModel() {
    fun searchProduct(query: String){

    }
}
