package com.aibles.pstore.di

import android.content.Context
import com.aibles.pstore.data.remote.AuthenticateService
import com.aibles.pstore.data.remote.CartService
import com.aibles.pstore.data.remote.OrderService
import com.aibles.pstore.data.remote.ProductService
import com.aibles.pstore.utils.remote.AuthInterceptor
import com.aibles.pstore.utils.remote.CallAdapterFactory
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule{
    @Singleton
    @Provides
    fun provideHttpLogging() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideChucker(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

    @Singleton
    @Provides
    fun provideStetho() = StethoInterceptor()

    @Singleton
    @Provides
    fun provideClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        stethoInterceptor: StethoInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(stethoInterceptor)
            .addInterceptor(AuthInterceptor())
            .addInterceptor(chuckerInterceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

    @Singleton
    @Provides
    fun provideGson() =
        Gson().newBuilder()
            .serializeNulls()
            .create()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.pstore.ml/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit) =
        retrofit.create(AuthenticateService::class.java)

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit) =
        retrofit.create(ProductService::class.java)

    @Singleton
    @Provides
    fun provideCartService(retrofit: Retrofit) =
        retrofit.create(CartService::class.java)

    @Singleton
    @Provides
    fun provideOrderService(retrofit: Retrofit) =
        retrofit.create(OrderService::class.java)
}