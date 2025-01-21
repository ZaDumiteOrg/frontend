package com.example.zadumite_frontend.utils.notifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val context = applicationContext

        val notificationHandler = NotificationHandler(context)
        notificationHandler.showWordOfWeekNotification()

        return Result.success()
    }
}
