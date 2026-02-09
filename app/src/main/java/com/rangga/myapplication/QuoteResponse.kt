package com.rangga.myapplication

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    val id: Int,
    val quote: String, // DummyJSON menggunakan nama 'quote'
    val author: String
)