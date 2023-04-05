package com.montagat.view_state

import com.montagat.model.MontagModel


sealed class MontagViewState {


    //Idle of a Data
    object Idle : MontagViewState()

    //Loading of a Data
    object Loading : MontagViewState()

    //Fetch of a Data
    data class getMontag(val montag: List<MontagModel>) : MontagViewState()

    //Error of a Data
    data class Error(val error: String) : MontagViewState()

}