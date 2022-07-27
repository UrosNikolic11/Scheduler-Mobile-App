package rs.raf.projekat2uros_nikolic_2619rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.AddBeleskaState
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.RasporedState

interface MainContract {

    interface ViewModel {
        val rasporedState: LiveData<RasporedState>
        val addDone: LiveData<AddBeleskaState>
        fun fetchAll()
        fun getAll()
        fun getAllByGrupaDanPP(grupa: String, dan: String, text: String)
    }

}