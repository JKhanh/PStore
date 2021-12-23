package com.aibles.pstore.data.repository

import com.aibles.pstore.data.remote.AuthenticateService
import com.aibles.pstore.model.entities.Account
import com.aibles.pstore.model.entities.ProfileResponse
import com.aibles.pstore.model.entities.dto.AccountRegister
import com.aibles.pstore.model.entities.dto.BaseResponse
import com.aibles.pstore.utils.remote.NetworkAuthenticationException
import com.aibles.pstore.utils.remote.Resource
import com.orhanobut.hawk.Hawk
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor (
    private val authenticateService: AuthenticateService
): AuthenticationRepository {
    override suspend fun login(account: Account): Resource<Nothing?> {
        val response = authenticateService.signIn(account)
        if(response.isSuccessful()){
            response.data?.let{
                Hawk.put("token", it.token)
                return Resource.success(null)
            }
        }
        return Resource.error(NetworkAuthenticationException("Wrong"))
    }

    override suspend fun signup(accountRegister: AccountRegister): Resource<BaseResponse> {
        return authenticateService.signUp(accountRegister)
    }

    override suspend fun getProfile(): Resource<ProfileResponse> {
        return authenticateService.getUserProfile()
    }
}