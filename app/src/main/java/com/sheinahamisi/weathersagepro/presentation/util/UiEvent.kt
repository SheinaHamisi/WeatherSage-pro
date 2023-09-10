package com.sheinahamisi.weathersagepro.presentation.util

sealed class UiEvent {
    object Success : UiEvent()
    object ShowToast : UiEvent()
    object ShowBottomSheet : UiEvent()
    object Share : UiEvent()
    object OnLaunch : UiEvent()
}
