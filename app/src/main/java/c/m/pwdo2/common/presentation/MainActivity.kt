package c.m.pwdo2.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import c.m.pwdo2.common.presentation.screen.WrapperScreen
import c.m.pwdo2.common.presentation.ui.theme.PWDO2Theme
import c.m.pwdo2.oxygen_requirement_and_area.presentation.view_model.OxygenRequirementAndAreaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val oxygenRequirementAndAreaViewModel: OxygenRequirementAndAreaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.w("ON CREATE")

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PWDO2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WrapperScreen()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Timber.w("ON PAUSE")
        oxygenRequirementAndAreaViewModel.doLogout()
    }

    override fun onStop() {
        super.onStop()
        Timber.w("ON STOP")
        oxygenRequirementAndAreaViewModel.doLogout()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.w("ON DESTROY")
        oxygenRequirementAndAreaViewModel.doLogout()
    }
}