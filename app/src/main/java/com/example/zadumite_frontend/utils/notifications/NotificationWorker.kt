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

        val type = inputData.getString("type")

        val notificationHandler = NotificationHandler(applicationContext)

        when (type) {
            "daily" -> {
                notificationHandler.showDailyQuestionNotification()
            }
            "weekly" -> {
                notificationHandler.showWordOfWeekNotification()
            }
            else -> Log.w("NotificationWorker", "Unknown notification type")
        }

        return Result.success()
    }

}
