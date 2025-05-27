package com.example.zadumite_frontend

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.zadumite_frontend.navigation.NavigationStack
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.zadumite_frontend.utils.notifications.scheduleDailyNotification
import com.example.zadumite_frontend.utils.notifications.scheduleWeeklyNotification

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show()
                scheduleWeeklyNotification(applicationContext)
                scheduleDailyNotification(applicationContext)
            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()

        setContent {
            NavigationStack()
        }
    }

    private fun createNotificationChannel() {
        val name = "Daily Question"
        val descriptionText = "Daily popup for new questions"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("daily_question_channel", name, importance).apply {
            description = descriptionText
        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Notification permission already granted", Toast.LENGTH_SHORT).show()

                scheduleWeeklyNotification(applicationContext)
                scheduleDailyNotification(applicationContext)
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            Toast.makeText(this, "Notification permission not required for this Android version", Toast.LENGTH_SHORT).show()

            scheduleWeeklyNotification(applicationContext)
            scheduleDailyNotification(applicationContext)
        }
    }

}
