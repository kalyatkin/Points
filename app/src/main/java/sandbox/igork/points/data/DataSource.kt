package sandbox.igork.points.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sandbox.igork.points.network.PointsRestService
import javax.inject.Inject
import kotlin.random.Random


class DataSource @Inject constructor() {
    private val random = Random(System.currentTimeMillis())

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hr-challenge.interactivestandard.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(PointsRestService::class.java)

    suspend fun getPoints(count: Int): List<PointDto>? {
        return service.getPoints(count).points
    }
}