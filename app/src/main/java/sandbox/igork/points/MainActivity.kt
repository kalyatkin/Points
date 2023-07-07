@file:OptIn(ExperimentalMaterial3Api::class)

package sandbox.igork.points

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import sandbox.igork.points.di.appComponent
import sandbox.igork.points.ui.MainScreen
import sandbox.igork.points.ui.theme.PointsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        setContent {
            PointsTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}