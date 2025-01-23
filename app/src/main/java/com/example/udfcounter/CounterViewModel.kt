package com.example.udfcounter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel: ViewModel() {
    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state.asStateFlow()

    fun dispatch(action: CounterAction){
        when(action)
        {
            CounterAction.Increment -> _state.update { it.copy(count = it.count+1) }
            CounterAction.Decrement -> _state.update {  it.copy(count = it.count-1)  }
            CounterAction.Reset -> _state.update {  it.copy(count = 0)  }
        }
    }

}