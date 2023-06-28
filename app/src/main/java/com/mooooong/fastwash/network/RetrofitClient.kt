package com.mooooong.fastwash.network

import com.mooooong.fastwash.network.service.AssignmentService
import com.mooooong.fastwash.network.service.AuthService
import com.mooooong.fastwash.network.service.WasherService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://server.fastwash.kro.kr:8080"
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val assignmentService: AssignmentService
    val authService: AuthService
    val washerService: WasherService

    private val okhttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

    init {
        assignmentService = retrofit.create(AssignmentService::class.java)
        authService = retrofit.create(AuthService::class.java)
        washerService = retrofit.create(WasherService::class.java)
    }
}