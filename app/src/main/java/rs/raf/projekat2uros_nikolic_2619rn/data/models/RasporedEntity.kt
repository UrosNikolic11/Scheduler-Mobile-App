package rs.raf.projekat2uros_nikolic_2619rn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rasporeds")
data class RasporedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
)