package rs.raf.projekat2uros_nikolic_2619rn.data.repositories


import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2uros_nikolic_2619rn.data.datasources.local.BeleskeDao
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Beleske
import rs.raf.projekat2uros_nikolic_2619rn.data.models.BeleskeEntity

class BeleskeRepositoryImpl (private val localDataSource: BeleskeDao): BeleskeRepository{
    override fun getAllBeleske(): Observable<List<Beleske>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Beleske(it.id, it.naslov,it.sadrzaj,it.arhiviran)
                }
            }
    }

    override fun insertBeleske(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean): Completable {
        val beleskaEntity = BeleskeEntity(id, naslov, sadrzaj, arhiviran)
        return localDataSource.insert(beleskaEntity)
    }

    override fun updateBeleske(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean): Completable {
        return Completable.fromCallable {
            localDataSource.updateById(id, naslov, sadrzaj, arhiviran)
        }
    }

    override fun deleteBeleske(id: Long?): Completable {
        return Completable.fromCallable {
            localDataSource.deleteById(id)
        }
    }

    override fun getAllByNaslovSadrzaj(filter: String): Observable<List<Beleske>> {
        return localDataSource.
            getAllByNaslovSadrzaj(filter)
            .map {
            it.map {
                Beleske(it.id, it.naslov,it.sadrzaj,it.arhiviran)
            }
        }
    }

    override fun getAllByArhiviran(): Observable<List<Beleske>> {
        return localDataSource.
        getAllByArhiviran()
            .map {
                it.map {
                    Beleske(it.id, it.naslov,it.sadrzaj,it.arhiviran)
                }
            }
    }

}