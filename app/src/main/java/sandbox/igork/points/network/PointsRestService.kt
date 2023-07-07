package sandbox.igork.points.network

import retrofit2.http.GET
import retrofit2.http.Query
import sandbox.igork.points.data.PointDto
import sandbox.igork.points.data.PointsDto

interface PointsRestService {

    @GET("/api/test/points")
    suspend fun getPoints(@Query("count") count: Int): PointsDto
}