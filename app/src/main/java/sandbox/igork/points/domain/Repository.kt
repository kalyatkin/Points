package sandbox.igork.points.domain

interface Repository {
    suspend fun getPoints(count: Int): List<DataPoint>
}