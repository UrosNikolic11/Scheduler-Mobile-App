package rs.raf.projekat2uros_nikolic_2619rn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beleske")
data class BeleskeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val naslov: String,
    val sadrzaj: String,
    val arhiviran: Boolean
)
