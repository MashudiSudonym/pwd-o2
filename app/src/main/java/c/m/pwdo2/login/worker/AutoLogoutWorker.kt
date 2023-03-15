package c.m.pwdo2.login.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import c.m.pwdo2.common.util.Resource
import c.m.pwdo2.login.domain.use_case.UserLogoutUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class AutoLogoutWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameter: WorkerParameters,
    private val userLogoutUseCase: UserLogoutUseCase,
) : CoroutineWorker(context, workerParameter) {
    override suspend fun doWork(): Result {
        return try {
            userLogoutUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> Result.failure()
                    is Resource.Loading -> Unit
                    is Resource.Success -> Timber.d(result.data?.toString())
                }
            }
            Timber.i("Worker it's working!")
            Result.success()
        } catch (e: Throwable) {
            Timber.i("Worker it's not working, retrying...")
            Result.retry()
        }
    }
}