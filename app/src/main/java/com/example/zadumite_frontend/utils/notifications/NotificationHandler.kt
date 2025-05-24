package com.example.zadumite_frontend.utils.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.zadumite_frontend.R
import kotlin.random.Random

class NotificationHandler(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = context.getString(R.string.notification_channel_id)

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val name = context.getString(R.string.notification_channel_name)
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(notificationChannelID, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }
    fun showWordOfWeekNotification() {
        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_text))
            .setSmallIcon(R.drawable.notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }

    fun showDailyQuestionNotification() {
        val channelId = context.getString(R.string.daily_question_channel_id)

        val name = context.getString(R.string.daily_question_channel_name)
        val descriptionText = context.getString(R.string.daily_question_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(context.getString(R.string.daily_question_notification_title))
            .setContentText(context.getString(R.string.daily_question_notification_text))
            .setSmallIcon(R.drawable.notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }

}