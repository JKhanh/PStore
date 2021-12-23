package com.aibles.pstore.di

import com.aibles.pstore.data.remote.ProductService
import com.aibles.pstore.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Singleton
    @Binds
    abstract fun bindCartRepository(impl: CartRepositoryImpl): CartRepository

    @Singleton
    @Binds
    abstract fun bindAuthenticationRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Singleton
    @Binds
    abstract fun bindOrderRepository(impl: OrderRepositoryImpl): OrderRepository
}