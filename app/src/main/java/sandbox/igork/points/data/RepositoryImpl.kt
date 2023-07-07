package sandbox.igork.points.data

import sandbox.igork.points.domain.DataPoint
import sandbox.igork.points.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource) : Repository {
    override suspend fun getPoints(count: Int): List<DataPoint> =
        dataSource
            .getPoints(count)
            .orEmpty()
            .mapNotNull {
                if (it.x != null && it.y != null)
                    DataPoint(it.x, it.y)
                else
                    null
            }
}