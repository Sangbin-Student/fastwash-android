package com.mooooong.fastwash.network.service

import com.mooooong.fastwash.network.response.AuthResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("auth/sign-in")
    suspend fun signIn(
        @Query("code") code: String,
    ): AuthResponse

    @POST("auth/sign-in-token")
    suspend fun signInToken(
        @Query("accessToken") accessToken: String,
    ): AuthResponse
}
