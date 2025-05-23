package com.example.zadumite_frontend.data.model.session
import com.example.zadumite_frontend.data.model.token.JwtTokenManager

class DefaultSessionManager(
    private val tokenManager: JwtTokenManager,
    private val logoutNotifier: LogoutNotifier
) : SessionManager {
    override suspend fun logout() {
        tokenManager.clearAllTokens()
        logoutNotifier.notifyLogout()
    }
}
