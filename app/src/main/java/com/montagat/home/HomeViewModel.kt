package com.montagat.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montagat.intent.MontagIntent
import com.montagat.repository.MontagRepository
import com.montagat.view_state.MontagViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    private val montagRepository = MontagRepository()

    val montagatChannel = Channel<MontagIntent>(Channel.UNLIMITED)

    private val viewState = MutableStateFlow<MontagViewState>(MontagViewState.Idle)
    val state: StateFlow<MontagViewState> get() = viewState


    init {
        montagatIntent()
    }


    private fun montagatIntent() {
        viewModelScope.launch {
            montagatChannel.consumeAsFlow().collect {
                when (it) {
                    is MontagIntent.fetchMontagat -> fetchMontagat()
                }
            }
        }
    }


    private fun fetchMontagat() {
        viewModelScope.launch {
            viewState.value = MontagViewState.Loading
            viewState.value =
                try {
                    MontagViewState.getMontag(montagRepository.getMontagat().body()!!.montag.montagModel.toList())
            } catch (e: Exception) {
                MontagViewState.Error(e.message.toString())
            }
        }
    }

}