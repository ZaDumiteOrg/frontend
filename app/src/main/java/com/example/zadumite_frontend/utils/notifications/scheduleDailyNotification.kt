package com.example.zadumite_frontend.utils.notifications
import android.content.Context
import androidx.work.WorkManager
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import java.util.concurrent.TimeUnit

import androidx.work.PeriodicWorkRequestBuilder
import java.util.Calendar

fun scheduleDailyNotification(context: Context) {
    val inputData = Data.Builder()
        .putString("type", "daily")
        .build()

    val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 10)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)

        if (before(Calendar.getInstance())) {
            add(Calendar.DAY_OF_YEAR, 1)
        }
    }

    val initialDelay = calendar.timeInMillis - System.currentTimeMillis()

    val dailyWorkRequest = PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.DAYS)
        .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
        .setInputData(inputData)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "DailyQuestionNotification",
        ExistingPeriodicWorkPolicy.UPDATE,
        dailyWorkRequest
    )
}
