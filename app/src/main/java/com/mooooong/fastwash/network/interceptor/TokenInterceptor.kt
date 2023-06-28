package com.mooooong.fastwash.network.interceptor

import android.content.Context
import com.mooooong.fastwash.local.FastWashDatabase
import com.mooooong.fastwash.local.entity.TokenEntity
import com.mooooong.fastwash.utils.FastWashApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var token: TokenEntity?

        val context: Context = FastWashApplication.instance
        val fastWashDatabase = FastWashDatabase.getInstance(context)

        runBlocking(Dispatchers.IO) {
            token = fastWashDatabase?.tokenDao()?.getToken()
        }

        val request = chain.request().newBuilder()
            .header("authorization", "Bearer ${token?.token ?: ""}")
            .build()

        return chain.proceed(request)
    }
}