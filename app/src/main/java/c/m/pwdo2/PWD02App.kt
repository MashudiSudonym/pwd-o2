package c.m.pwdo2

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import c.m.pwdo2.login.worker.AutoLogoutWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class PWD02App : Application(), Configuration.Provider {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    private val applicationScope = CoroutineScope(Dispatchers.IO)

    override fun getWorkManagerConfiguration(): Configuration {
        return if (BuildConfig.DEBUG) {
            Configuration.Builder()
                .setWorkerFactory(hiltWorkerFactory)
                .setMinimumLoggingLevel(android.util.Log.DEBUG)
                .build()
        } else {
            Configuration.Builder()
                .setWorkerFactory(hiltWorkerFactory)
                .setMinimumLoggingLevel(android.util.Log.ERROR)
                .build()
        }
    }

    override fun onCreate() {
        super.onCreate()

        // running Worker
        delayInit()

        Timber.plant(Timber.DebugTree())
    }

    private fun delayInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val repeatingRequest =
            PeriodicWorkRequestBuilder<AutoLogoutWorker>(1, TimeUnit.HOURS).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "AUTO LOGOUT WORKER",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            repeatingRequest
        )
    }
}