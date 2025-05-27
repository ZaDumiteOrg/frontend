package com.example.zadumite_frontend.utils.notifications

import android.content.Context
import androidx.work.WorkManager
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import java.util.concurrent.TimeUnit

import androidx.work.PeriodicWorkRequestBuilder
import java.util.Calendar

fun scheduleWeeklyNotification(context: Context) {
    val testMode = true

    val inputData = Data.Builder()
        .putString("type", "weekly")
        .putString("title", "Дума на седмицата")
        .putString("message", "Узнай думата на седмицата!")
        .build()

    if (testMode) {
        val testWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(30, TimeUnit.SECONDS)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "TestNotification",
            ExistingWorkPolicy.REPLACE,
            testWorkRequest
        )
    } else {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val currentTime = System.currentTimeMillis()
        var notificationTime = calendar.timeInMillis
        if (notificationTime <= currentTime) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
            notificationTime = calendar.timeInMillis
        }

        val initialDelay = notificationTime - currentTime

        val weeklyWorkRequest = PeriodicWorkRequestBuilder<NotificationWorker>(7, TimeUnit.DAYS)
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "WeeklyNotification",
            ExistingPeriodicWorkPolicy.UPDATE,
            weeklyWorkRequest
        )
    }
}
