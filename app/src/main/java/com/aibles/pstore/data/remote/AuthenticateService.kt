package com.aibles.pstore.data.remote

import com.aibles.pstore.model.entities.Account
import com.aibles.pstore.model.entities.ProfileResponse
import com.aibles.pstore.model.entities.dto.AccountRegister
import com.aibles.pstore.model.entities.dto.BaseResponse
import com.aibles.pstore.model.entities.dto.LoginResponse
import com.aibles.pstore.utils.remote.Resource
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticateService {
    @POST("signin/")
    suspend fun signIn(@Body account: Account): Resource<LoginResponse>

    @POST("signup/")
    suspend fun signUp(@Body accountRegister: AccountRegister): Resource<BaseResponse>

    @GET("profile/")
    suspend fun getUserProfile(): Resource<ProfileResponse>
}