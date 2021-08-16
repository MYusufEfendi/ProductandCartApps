package com.yusuf.app.testtwiscode.network

import com.yusuf.app.testtwiscode.model.ProductResponse
import retrofit2.Response
import retrofit2.http.POST

interface ApiService {
    @POST("teampsisthebest")
    suspend fun getAll(): Response<List<ProductResponse>>
}