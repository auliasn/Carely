package com.example.carely

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {

    @SuppressLint("ServiceCast")
    override fun onReceive(context: Context, intent: Intent?) {
        val channelId = "alarm_channel_new"   // GANTI ID BARU!

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // === SUARA ALARM ===
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val channel = NotificationChannel(
                channelId,
                "Alarm Obat",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel untuk alarm pengingat obat"
                setSound(soundUri, audioAttributes)
                enableVibration(true)
            }

            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.herbal_capsule)
            .setContentTitle("Pengingat Obat")
            .setContentText("Sudah waktunya minum obat!")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(soundUri)   // Tambahan untuk Android 12 ke bawah

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())

        val popupIntent = Intent(context, PopupActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

            // Jika kamu ingin membawa data obat ke popup:
            putExtra("ID_OBAT", intent?.getIntExtra("ID_OBAT", -1))
            putExtra("NAMA_OBAT", intent?.getStringExtra("NAMA_OBAT"))
        }

        context.startActivity(popupIntent)

    }
}