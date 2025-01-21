package com.example.zadumite_frontend.utils.notifications

import android.content.Context
import androidx.work.WorkManager
import androidx.work.Data
import java.util.concurrent.TimeUnit

import androidx.work.PeriodicWorkRequestBuilder
import java.util.Calendar

fun scheduleWeeklyNotification(context: Context) {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        set(Calendar.HOUR_OF_DAY, 9)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }

    val currentTime = System.currentTimeMillis()
    val notificationTime = calendar.timeInMillis

    if (notificationTime <= currentTime) {
        calendar.add(Calendar.WEEK_OF_YEAR, 1)
    }

    val initialDelay = calendar.timeInMillis - currentTime

    val inputData = Data.Builder()
        .putString("title", "Дума на седмицата")
        .putString("message", "Узнай думата на седмицата!")
        .build()

    val weeklyWorkRequest = PeriodicWorkRequestBuilder<NotificationWorker>(7, TimeUnit.DAYS)
        .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
        .setInputData(inputData)
        .build()

    WorkManager.getInstance(context).enqueue(weeklyWorkRequest)
}
