package com.montagat.repository

import com.montagat.network.remote.ApiMontag
import com.montagat.response.MontagResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MontagRepository {


    suspend fun getMontagat(): Response<MontagResponse> {
        return ApiMontag.getApiNew.getMontagat()
    }
}