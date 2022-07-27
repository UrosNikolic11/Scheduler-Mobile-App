package rs.raf.projekat2uros_nikolic_2619rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Raspored
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Resource

interface RasporedRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Raspored>>
    fun getAllByName(name: String): Observable<List<Raspored>>
    fun insert(raspored: Raspored): Completable
    fun getAllByGrupa(grupa: String): Observable<List<Raspored>>
    fun getAllByDan(dan: String): Observable<List<Raspored>>
    fun getAllByPP(pp: String): Observable<List<Raspored>>
    fun getAllByGrupaDan(grupa: String, dan: String): Observable<List<Raspored>>
    fun getAllByGrupaPP(grupa: String, pp: String): Observable<List<Raspored>>
    fun getAllByDanPP(dan: String, pp: String): Observable<List<Raspored>>
    fun getAllByGrupaDanPP(grupa: String, dan: String, pp: String): Observable<List<Raspored>>
}