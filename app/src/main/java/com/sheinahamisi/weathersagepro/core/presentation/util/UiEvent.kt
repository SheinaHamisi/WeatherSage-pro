package com.sheinahamisi.weathersagepro.core.presentation.util

import com.sheinahamisi.weathersagepro.core.util.UiText

sealed class UiEvent {
    object Success : UiEvent()
    object ShowToast : UiEvent()
    object ShowBottomSheet : UiEvent()
    object Share : UiEvent()
    object RequestForPermission : UiEvent()
}
