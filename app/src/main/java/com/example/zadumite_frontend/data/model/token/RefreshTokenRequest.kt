package com.example.zadumite_frontend.data.model.token

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest (
    @SerializedName("refresh_token") val refreshToken: String
)

