package com.rangga.myapplication

import com.google.gson.annotations.SerializedName

data class IndoQuoteResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("quote") val quote: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("category") val category: String? = null
)
