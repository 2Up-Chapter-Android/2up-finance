package com.aibles.account.data.remote

import com.aibles.account.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST
import com.aibles.finance.data.remote.util.Resource


interface RegisterService {
    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Resource<Boolean>
}