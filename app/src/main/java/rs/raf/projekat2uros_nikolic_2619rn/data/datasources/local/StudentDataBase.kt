package rs.raf.projekat2uros_nikolic_2619rn.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2uros_nikolic_2619rn.data.models.BeleskeEntity
import rs.raf.projekat2uros_nikolic_2619rn.data.models.RasporedEntity

@Database(
    entities = [RasporedEntity::class, BeleskeEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class StudentDataBase : RoomDatabase() {
    abstract fun getRasporedDao(): RasporedDao
    abstract fun getBeleskeDao(): BeleskeDao
}