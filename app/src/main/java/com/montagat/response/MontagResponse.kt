package com.montagat.response


import com.google.gson.annotations.SerializedName

data class MontagResponse(
    @SerializedName("data")
    val montag: Montag,
)