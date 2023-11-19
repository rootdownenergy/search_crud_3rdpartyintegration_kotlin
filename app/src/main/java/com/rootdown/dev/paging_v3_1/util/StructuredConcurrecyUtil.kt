package com.rootdown.dev.paging_v3_1.util

import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class StructuredConcurrecyUtil {

}
class SingleShotEventBus {
    private val _events = Channel<Lifecycle.Event>()
    val events = _events.receiveAsFlow() // expose as flow

    suspend fun postEvent(event: Lifecycle.Event) {
        _events.send(event) // suspends on buffer overflow
    }
}

//class StateModel {
    //private val _state = MutableStateFlow()
    //val state = _state.asStateFlow() // read-only public view

    //fun update(newValue: Value) {
        //_state.value = newValue // NOT suspending
    //}
//}