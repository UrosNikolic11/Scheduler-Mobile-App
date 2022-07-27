package rs.raf.projekat2uros_nikolic_2619rn.data.repositories


import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Beleske

interface BeleskeRepository {
    fun getAllBeleske(): Observable<List<Beleske>>
    fun insertBeleske(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean): Completable
    fun updateBeleske(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean): Completable
    fun deleteBeleske(id: Long?): Completable
    fun getAllByNaslovSadrzaj(filter: String): Observable<List<Beleske>>
    fun getAllByArhiviran(): Observable<List<Beleske>>
}