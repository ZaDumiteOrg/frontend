package com.example.zadumite_frontend.utils.token

import android.util.Log

object TokenUtils {
    fun decodeUserIdFromToken(token: String): Int? {
        return try {
            val parts = token.split(".")
            if (parts.size == 3) {
                val payload = parts[1]
                val paddedPayload = when (payload.length % 4) {
                    2 -> "$payload=="
                    3 -> "$payload="
                    else -> payload
                }
                val decodedPayload = String(android.util.Base64.decode(paddedPayload, android.util.Base64.URL_SAFE or android.util.Base64.NO_WRAP))
                val json = org.json.JSONObject(decodedPayload)
                json.optInt("sub", -1).takeIf { it != -1 }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("TokenDecode", "Failed to decode token: ${e.message}")
            null
        }
    }

    fun decodeUserRoleFromToken(token: String): String? {
        return try {
            val parts = token.split(".")
            if (parts.size == 3) {
                val payload = parts[1]
                val paddedPayload = when (payload.length % 4) {
                    2 -> "$payload=="
                    3 -> "$payload="
                    else -> payload
                }
                val decodedPayload = String(
                    android.util.Base64.decode(
                        paddedPayload,
                        android.util.Base64.URL_SAFE or android.util.Base64.NO_WRAP
                    )
                )
                val json = org.json.JSONObject(decodedPayload)
                json.optString("roles") ?: null
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("TokenDecode", "Failed to decode token: ${e.message}")
            null
        }
    }
}