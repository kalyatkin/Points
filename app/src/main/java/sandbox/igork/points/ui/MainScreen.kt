package sandbox.igork.points.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import sandbox.igork.points.presentation.PointsViewModel
import sandbox.igork.points.presentation.ViewState

@Composable
fun MainScreen(pointsViewModel: PointsViewModel = viewModel()) {
    when (val state = pointsViewModel.viewState.collectAsState().value) {
        is ViewState.Content -> MainScreenContent(state, pointsViewModel)
        is ViewState.Error -> MainScreenError(state, pointsViewModel)
        ViewState.Progress -> MainScreenProgress()
    }
}

@Composable
fun MainScreenContent(
    content: ViewState.Content,
    pointsViewModel: PointsViewModel,
    graphViewModel: GraphViewModel = viewModel()
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    LaunchedEffect(content) {
        graphViewModel.setPoints(content.points, screenWidthDp.dp)
    }

    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
        MainScreenLandscape(content, pointsViewModel)
    else
        MainScreenPortrait(content, pointsViewModel)
}

@Composable
fun MainScreenLandscape(content: ViewState.Content, pointsViewModel: PointsViewModel) {
    Column() {
        HeaderLandscape(content.pointsCount, pointsViewModel)
        PointsTable(content.points)
        Graph()
    }
}

@Composable
fun MainScreenPortrait(content: ViewState.Content, pointsViewModel: PointsViewModel) {
    Column {
        HeaderPortrait(content.pointsCount, pointsViewModel)
        PointsTable(content.points)
        Graph()
    }
}

@Composable
fun MainScreenError(error: ViewState.Error, pointsViewModel: PointsViewModel) {
    Column {
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
            HeaderLandscape(error.pointsCount, pointsViewModel)
        else
            HeaderPortrait(pointsCount = error.pointsCount, pointsViewModel = pointsViewModel)

        Text(
            text = error.message,
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun MainScreenProgress() {
    CircularProgressIndicator(modifier = Modifier.fillMaxSize())
}
