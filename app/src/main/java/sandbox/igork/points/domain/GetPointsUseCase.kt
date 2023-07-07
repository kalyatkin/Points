package sandbox.igork.points.domain

import javax.inject.Inject


class GetPointsUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(count: Int): List<DataPoint> =
        repository.getPoints(count).sortedBy { it.x }
}