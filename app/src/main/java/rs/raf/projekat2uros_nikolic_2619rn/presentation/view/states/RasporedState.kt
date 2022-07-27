package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states

import rs.raf.projekat2uros_nikolic_2619rn.data.models.Raspored

sealed class RasporedState {
    object Loading: RasporedState()
    object DataFetched: RasporedState()
    data class Success(val raspored: List<Raspored>): RasporedState()
    data class Error(val message: String): RasporedState()
}