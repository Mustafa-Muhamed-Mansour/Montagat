package com.montagat.network.remote

import com.montagat.common.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiMontag {


    companion object
    {

        private val retrofit by lazy {

            Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val getApiNew by lazy {
            retrofit.create(MontagService::class.java)
        }
    }

}