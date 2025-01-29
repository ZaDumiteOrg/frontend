package com.example.zadumite_frontend.data.model.user

import com.google.gson.annotations.SerializedName

data class SignUpResponse (
    val user: User,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String
)