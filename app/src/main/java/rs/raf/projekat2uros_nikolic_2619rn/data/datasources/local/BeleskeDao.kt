package rs.raf.projekat2uros_nikolic_2619rn.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2uros_nikolic_2619rn.data.models.BeleskeEntity

@Dao
abstract class BeleskeDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: BeleskeEntity): Completable

    @Query("SELECT * FROM beleske")
    abstract fun getAll(): Observable<List<BeleskeEntity>>

    @Update
    abstract fun update(beleskeEntity: BeleskeEntity)

    @Query("UPDATE beleske SET naslov = :naslov, sadrzaj = :sadrzaj, arhiviran = :arhiviran WHERE id = :id")
    abstract fun updateById(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean)

    @Delete
    abstract fun delete(beleskeEntity: BeleskeEntity): Completable

    @Query("DELETE FROM beleske WHERE id = :id")
    abstract fun deleteById(id: Long?)

    @Query("SELECT * FROM beleske WHERE naslov LIKE '%'|| :filter || '%' OR sadrzaj LIKE '%' || :filter || '%'")
    abstract fun getAllByNaslovSadrzaj(filter: String): Observable<List<BeleskeEntity>>

    @Query("SELECT * FROM beleske WHERE arhiviran = 1")
    abstract fun getAllByArhiviran(): Observable<List<BeleskeEntity>>
}