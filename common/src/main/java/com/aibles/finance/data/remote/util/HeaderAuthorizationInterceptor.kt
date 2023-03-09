package com.aibles.finance.data.remote.util

import com.aibles.finance.data.local.HawkDataSource
import okhttp3.Interceptor
import okhttp3.Response

class HeaderAuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val jwt = HawkDataSource.getAccessToken()
        val headers = request.headers.newBuilder().add("authorization", "Bearer $jwt").build()
        request = request.newBuilder().headers(headers).build()
        return chain.proceed(request)
    }
}
