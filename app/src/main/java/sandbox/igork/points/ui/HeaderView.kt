package sandbox.igork.points.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import sandbox.igork.points.R
import sandbox.igork.points.presentation.PointsViewModel

@Composable
fun HeaderPortrait(pointsCount: Int, pointsViewModel: PointsViewModel) {
    Column {
        Text(text = stringResource(R.string.task_description))

        Row {

            TextField(
                value = pointsCount.toString(),
                onValueChange = { pointsViewModel.updatePointsCount(it) },
                label = { Text("Введите число точек:") },
            )

            Button(
                onClick = { pointsViewModel.requestPoints() },
                content = { Text(text = "Поехали") },
            )
        }
    }
}

@Composable
fun HeaderLandscape(pointsCount: Int, pointsViewModel: PointsViewModel) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(R.string.task_description))

        Column {

            TextField(
                value = pointsCount.toString(),
                onValueChange = { pointsViewModel.updatePointsCount(it) },
                label = { Text("Введите число точек:") },
            )

            Button(
                onClick = { pointsViewModel.requestPoints() },
                content = { Text(text = "Поехали") },
            )
        }
    }
}


private const val POINTS_COUNT = 5

@Composable
@Preview
private fun HeaderPreviewPortrait() {
    HeaderPortrait(
        pointsCount = POINTS_COUNT,
        pointsViewModel = viewModel(),
    )
}

@Composable
@Preview
private fun HeaderPreviewLandscape() {
    HeaderLandscape(
        pointsCount = POINTS_COUNT,
        pointsViewModel = viewModel(),
    )
}