package com.example.zadumite_frontend.utils.notifications

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.zadumite_frontend.data.model.datastore.JwtTokenDataStore
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import java.util.prefs.Preferences

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val jwtTokenManager: JwtTokenManager
) :  CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val context = applicationContext

        val accessToken = jwtTokenManager.getAccessJwt()

        if (accessToken.isNullOrEmpty()) {
            return Result.success()
        }

        val notificationHandler = NotificationHandler(context)
        notificationHandler.showWordOfWeekNotification()

        return Result.success()
    }
}
