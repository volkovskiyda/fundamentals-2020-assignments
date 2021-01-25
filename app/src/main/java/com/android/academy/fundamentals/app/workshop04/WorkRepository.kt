package com.android.academy.fundamentals.app.workshop04

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import java.util.concurrent.TimeUnit

class WorkRepository {
    //Todo 4.3: Create simple OneTimeWorkRequest
    //Todo 4.5: Create delayedRequest
    //Todo 4.7: Create constrainedRequest
    val simpleRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
    val delayedRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
        .setInitialDelay(5, TimeUnit.SECONDS).build()
    val constrainedRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
        .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
        .build()
}