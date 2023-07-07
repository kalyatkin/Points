package sandbox.igork.points.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import sandbox.igork.points.domain.DataPoint

@Composable
fun Graph(graphViewModel: GraphViewModel = viewModel()) {
    val state: State<GraphViewState> = graphViewModel.uiState.collectAsState()

    when (val value = state.value) {
        GraphViewState.Loading -> ShowProgress()
        is GraphViewState.Content -> ShowContent(value)
    }
}

@Composable
fun ShowProgress() {
    CircularProgressIndicator()
}

@Composable
fun ShowContent(state: GraphViewState.Content) {
    val size = state.data.linePlotData.lines.getOrNull(0)?.dataPoints?.size
    if (size != null && size > 0)
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = state.data
        )
}

private fun DataPoint.toGraphPoint(): Point =
    Point(this.x, this.y)

