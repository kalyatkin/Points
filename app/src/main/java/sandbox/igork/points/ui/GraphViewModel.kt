package sandbox.igork.points.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import sandbox.igork.points.domain.DataPoint
import kotlin.math.roundToInt

class GraphViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<GraphViewState>(GraphViewState.Loading)
    val uiState = _uiState.asStateFlow()

    suspend fun setPoints(newPoints: List<DataPoint>, screenWidth: Dp) {
        _uiState.value = GraphViewState.Loading
        _uiState.value = GraphViewState.Content(
            buildData(newPoints.map(DataPoint::asPoint), screenWidth)
        )
    }

    private suspend fun buildData(points: List<Point>, screenWidth: Dp): LineChartData {
        val xMin = points.minByOrNull { it.x }?.x ?: 0f
        val xMax = points.maxByOrNull { it.x }?.x ?: 0f
        val xDiff = xMax - xMin
        val xSteps = 10

        val xAxisData = AxisData.Builder()
//                    .axisStepSize(100.dp)
            .axisStepSize((screenWidth-100.dp) / (if (xDiff > 0) xDiff else xSteps.toFloat()))
//                    .backgroundColor(Color.Blue)
            .steps(
                xSteps
//                (points.maxByOrNull { it.x }?.x?.toInt() ?: 1)
//                        - (points.minByOrNull { it.x }?.x?.toInt() ?: 0)
            )
//            .labelData { i -> (xMin + xDiff * i / xSteps).roundToInt().toString() }
            .labelData { i -> (xMin.roundToInt() + i).toString() }
            .labelAndAxisLinePadding(15.dp)
            .build()

        val ySteps = 10
        val yMin = points.minByOrNull { it.y }?.y ?: 0f
        val yMax = points.maxByOrNull { it.y }?.y ?: 0f
        val yDiff = yMax - yMin
        val yAxisData = AxisData.Builder()
            .steps(ySteps)
//                    .backgroundColor(Color.Red)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i: Int ->
                (yMin + yDiff * i / ySteps).roundToInt().toString()
//                val yScale = 100 / ySteps
//                        (i * yScale).formatToSinglePrecision()
//                (i * yScale).toString()
            }.build()

        return LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = points,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(),
                        ShadowUnderLine(),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            gridLines = GridLines(),
            backgroundColor = Color.White
        )
    }

}

private fun DataPoint.asPoint(): Point = Point(x = this.x, y = this.y)
