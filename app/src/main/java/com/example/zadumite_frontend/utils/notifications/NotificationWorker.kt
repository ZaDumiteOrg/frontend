package com.example.zadumite_frontend.utils.notifications

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters,
) :  CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("NotificationWorker", "Showing notification")
        val notificationHandler = NotificationHandler(applicationContext)
        notificationHandler.showWordOfWeekNotification()
        return Result.success()
    }
}
