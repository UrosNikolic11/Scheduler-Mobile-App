package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states

import rs.raf.projekat2uros_nikolic_2619rn.data.models.Beleske

sealed class BeleskaState{
    object Loading: BeleskaState()
    data class Success(val beleska: List<Beleske>): BeleskaState()
    data class Error(val message: String): BeleskaState()
}
