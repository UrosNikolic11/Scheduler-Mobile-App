package rs.raf.projekat2uros_nikolic_2619rn.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2uros_nikolic_2619rn.data.models.RasporedEntity

@Dao
abstract class RasporedDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: RasporedEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<RasporedEntity>): Completable

    @Query("SELECT * FROM rasporeds")
    abstract fun getAll(): Observable<List<RasporedEntity>>

    @Query("DELETE FROM rasporeds")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<RasporedEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM rasporeds WHERE predmet LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<RasporedEntity>>

    @Query("SELECT * FROM rasporeds WHERE grupe LIKE '%' || :grupa || '%'")
    abstract fun getAllByGrupa(grupa: String): Observable<List<RasporedEntity>>

    @Query("SELECT * FROM rasporeds WHERE dan LIKE :dan")
    abstract fun getAllByDan(dan: String): Observable<List<RasporedEntity>>

    @Query("SELECT * FROM rasporeds WHERE predmet LIKE '%' || :pp || '%' OR nastavnik LIKE '%' || :pp || '%'")
    abstract fun getAllByPP(pp: String): Observable<List<RasporedEntity>>

    @Query("SELECT * FROM rasporeds WHERE dan LIKE :dan AND grupe LIKE '%' || :grupa || '%'")
    abstract fun getAllByGrupaDan(grupa: String, dan: String): Observable<List<RasporedEntity>>

    @Query("SELECT * FROM rasporeds WHERE grupe LIKE '%' || :grupa || '%' AND (predmet LIKE '%' || :pp || '%' OR nastavnik LIKE '%' || :pp || '%')")
    abstract fun getAllByGrupaPP(grupa: String, pp: String): Observable<List<RasporedEntity>>

    @Query("SELECT * FROM rasporeds WHERE dan LIKE :dan AND (nastavnik LIKE '%' || :pp || '%' OR predmet LIKE '%' || :pp || '%')")
    abstract fun getAllByDanPP(dan: String, pp: String): Observable<List<RasporedEntity>>

    @Query("SELECT * FROM rasporeds WHERE dan LIKE '%' || :dan || '%' AND grupe LIKE '%' || :grupa || '%' AND (nastavnik LIKE '%' || :pp || '%' OR predmet LIKE '%' || :pp || '%')")
    abstract fun getAllByGrupaDanPP(grupa: String, dan: String, pp: String): Observable<List<RasporedEntity>>
}