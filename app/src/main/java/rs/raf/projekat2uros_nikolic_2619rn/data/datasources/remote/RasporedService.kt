package rs.raf.projekat2uros_nikolic_2619rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat2uros_nikolic_2619rn.data.models.RasporedResponse

interface RasporedService {

    @GET("raspored/json.php")
    fun getAll(@Query("limit") limit: Int = 1000): Observable<List<RasporedResponse>>

}