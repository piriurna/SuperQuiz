package com.piriurna.superquiz

import androidx.lifecycle.ViewModel

abstract class SQBaseEventViewModel<E: SQBaseEvent> : ViewModel() {
    open fun onTriggerEvent(event: E) {}
}
