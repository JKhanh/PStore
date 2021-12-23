package com.aibles.pstore.data.repository

import com.aibles.pstore.model.entities.Account
import com.aibles.pstore.model.entities.ProfileResponse
import com.aibles.pstore.model.entities.dto.AccountRegister
import com.aibles.pstore.model.entities.dto.BaseResponse
import com.aibles.pstore.utils.remote.Resource

interface AuthenticationRepository {
    suspend fun login(account: Account): Resource<Nothing?>
    suspend fun signup(accountRegister: AccountRegister): Resource<BaseResponse>
    suspend fun getProfile(): Resource<ProfileResponse>
}