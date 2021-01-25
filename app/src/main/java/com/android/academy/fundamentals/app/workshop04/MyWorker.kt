package com.android.academy.fundamentals.app.workshop04

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    //Todo 4.1: Extend from Worker with Context&WorkerParameters
    //Todo 4.2: Override doWork() and write simple cycle for check internet connection with delay 1S

    override fun doWork(): Result {
        repeat(3) {
            Log.d("MyWorker", "doWork: $it")
            Thread.sleep(1_000)
            if (ConnectionChecker.isOnline()) return Result.success()
        }
        return Result.failure()
    }
}
