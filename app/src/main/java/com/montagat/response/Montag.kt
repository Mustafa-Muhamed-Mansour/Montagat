package com.montagat.response


import com.google.gson.annotations.SerializedName
import com.montagat.model.MontagModel



data class Montag(
    @SerializedName("data")
    val montagModel: List<MontagModel>,
)