package sandbox.igork.points.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sandbox.igork.points.domain.DataPoint


@Composable
fun PointsTable(points: List<DataPoint>) {
    val cellModifier = Modifier
        .border(0.dp, MaterialTheme.colorScheme.tertiary)
        .padding(2.dp)

    Row(
        Modifier
            .border(0.dp, MaterialTheme.colorScheme.tertiary)
            .padding(4.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(2.dp)) {
            Text(text = "X")
            Text(text = "Y")
        }
        for (point in points) {
            Spacer(modifier = Modifier.size(4.dp))
            Column(modifier = cellModifier) {
                Text(text = point.x.toString())
                Text(text = point.y.toString())
            }
        }
    }
}
