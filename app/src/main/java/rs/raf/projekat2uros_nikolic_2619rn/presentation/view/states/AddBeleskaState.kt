package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states

sealed class AddBeleskaState {
    object Success: AddBeleskaState()
    data class Error(val message: String): AddBeleskaState()
}