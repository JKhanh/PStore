package com.aibles.pstore.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aibles.pstore.data.remote.ProductService
import com.aibles.pstore.model.entities.Product
import java.io.IOException

class ProductDataSource(
    private val service: ProductService
): PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        try {
            val nextPageNumber = params.key ?: 0
            val response = service.getAllProduct(nextPageNumber*50)
            return LoadResult.Page(
                data = response.data!!.results,
                prevKey = null,
                nextKey = nextPageNumber+1
            )
        } catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}