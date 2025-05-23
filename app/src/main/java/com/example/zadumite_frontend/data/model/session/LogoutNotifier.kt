package com.example.zadumite_frontend.data.model.session

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class LogoutNotifier {
    private val _logoutFlow = MutableSharedFlow<Unit>(replay = 0)
    val logoutFlow: SharedFlow<Unit> = _logoutFlow

    suspend fun notifyLogout() {
        _logoutFlow.emit(Unit)
    }
}
