package com.montagat.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MontagModel(
    @SerializedName("description")
    val description: String,
//    @SerializedName("discount")
//    val discount: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("old_price")
    val oldPrice: Double,
    @SerializedName("price")
    val price: Double
) : Serializable