package com.example.movie.data.remote.interceptor

import com.example.movie.common.constants.ApiConstants
import com.example.movie.common.constants.SupportedLanguage
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Add query parameters to the original URL without modifying the path
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", ApiConstants.API_KEY)
            .addQueryParameter("language", SupportedLanguage.DEFAULT.languageCode)
            .build()

        // Create a new request with the modified URL
        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        return chain.proceed(modifiedRequest)
    }
}



