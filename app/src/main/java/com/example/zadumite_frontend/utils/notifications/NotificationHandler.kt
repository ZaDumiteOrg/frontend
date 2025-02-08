package com.example.zadumite_frontend.utils.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.zadumite_frontend.R
import kotlin.random.Random

class NotificationHandler(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = "new_word"

    fun showWordOfWeekNotification() {
        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle("Дума на седмицата")
            .setContentText("Узнай думата на седмицата!")
            .setSmallIcon(R.drawable.notification)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }
}