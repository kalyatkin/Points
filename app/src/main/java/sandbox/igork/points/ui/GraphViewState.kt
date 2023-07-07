package sandbox.igork.points.ui

import co.yml.charts.ui.linechart.model.LineChartData

sealed interface GraphViewState {
    object Loading : GraphViewState
    data class Content(val data: LineChartData) : GraphViewState
}