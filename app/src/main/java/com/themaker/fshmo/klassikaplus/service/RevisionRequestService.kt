package com.themaker.fshmo.klassikaplus.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.themaker.fshmo.klassikaplus.App
import com.themaker.fshmo.klassikaplus.R
import com.themaker.fshmo.klassikaplus.data.preferences.Preferences
import com.themaker.fshmo.klassikaplus.data.web.chain.ChainApi
import com.themaker.fshmo.klassikaplus.presentation.root.MainActivity
import java.io.IOException
import javax.inject.Inject


class RevisionRequestService(private val context: Context, workerParams: WorkerParameters) {

}
