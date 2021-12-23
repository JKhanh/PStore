package com.aibles.pstore.view.home

import com.aibles.pstore.ProductBindingModel_
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.utils.ErrorEpoxyModel_
import com.aibles.pstore.utils.LoadingEpoxyModel
import com.aibles.pstore.utils.LoadingEpoxyModel_
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel

class ProductPagingEpoxyController(
    private val onClick: ProductOnClick
): PagedListEpoxyController<Product>(modelBuildingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler(), diffingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler()) {
    private var isError: Boolean = false

    var error: String? = ""
        set(value) {
            field = value?.let {
                isError = true
                it
            } ?: run {
                isError = false
                null
            }
            if (isError) {
                requestModelBuild()
            }
        }

    var isLoading = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }


    override fun buildItemModel(currentPosition: Int, item: Product?): EpoxyModel<*> {
        item?.let { product ->
            return ProductBindingModel_()
                .id(product.id)
                .product(product)
                .onClick(onClick)
        } ?: run {
            return LoadingEpoxyModel_()
                .id("loading")
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if(isError){
            super.addModels(
                models.plus(
                    ErrorEpoxyModel_()
                        .id("error")
                ).filter { it !is LoadingEpoxyModel_ })
        } else if (isLoading) {
            super.addModels(
                models.plus(
                    LoadingEpoxyModel_()
                        .id("loading")
                ).distinct()
            )
        } else {
            super.addModels(models.distinct())
        }
    }
}