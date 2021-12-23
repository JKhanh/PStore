package com.aibles.pstore.utils.remote

import com.orhanobut.hawk.Hawk
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if(Hawk.contains("token")){
            val token = Hawk.get<String>("token")
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}