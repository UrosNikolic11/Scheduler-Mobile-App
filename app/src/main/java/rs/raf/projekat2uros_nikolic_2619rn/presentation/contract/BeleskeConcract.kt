package rs.raf.projekat2uros_nikolic_2619rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.AddBeleskaState
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.BeleskaState

interface BeleskeConcract {
    interface ViewModel {
        val beleskeState: LiveData<BeleskaState>
        val addDone: LiveData<AddBeleskaState>
        fun getAllBeleske()
        fun deleteBeleske(id: Long?)
        fun insertBeleske(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean)
        fun updateBeleske(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean)
        fun getAllByNaslovSadrzaj(filter: String)
        fun getAllByArhiviran()
    }
}