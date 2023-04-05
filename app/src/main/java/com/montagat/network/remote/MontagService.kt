package com.montagat.network.remote

import com.montagat.response.MontagResponse
import retrofit2.Response
import retrofit2.http.GET

interface MontagService {



    @GET("api/products")
    suspend fun getMontagat(): Response<MontagResponse>



}