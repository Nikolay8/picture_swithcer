package com.example.picture_switcher.utils

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.picture_switcher.R
import java.text.SimpleDateFormat
import java.util.*

class DownloadImage {
    fun download(activity: Activity, link: String) {
        if (ContextCompat.checkSelfPermission(
                activity, Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                activity, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                activity,
                activity.getString(R.string.need_permission),
                Toast.LENGTH_SHORT
            )
                .show()
        } else {
            Toast.makeText(
                activity,
                activity.getString(R.string.downloading_image),
                Toast.LENGTH_SHORT
            )
                .show()

            val calendar = Calendar.getInstance()
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)

            val uri: Uri = Uri.parse(link)
            val request = DownloadManager.Request(uri)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                timeStamp.format(calendar.time)
            )
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            val manager = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
        }
    }
}