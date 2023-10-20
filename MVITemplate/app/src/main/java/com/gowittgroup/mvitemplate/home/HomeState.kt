package com.gowittgroup.mvitemplate.home

import com.gowittgroup.commonui.base.ViewState
import com.gowittgroup.data.models.CallErrors
import com.gowittgroup.wrapper.models.Persona

sealed class HomeState : ViewState {
    object Loading : HomeState()
    data class ResultAllPersona(val data: List<Persona>): HomeState()
    data class ResultSearch(val data: List<Persona>): HomeState()
    data class Exception(val callErrors: CallErrors): HomeState()
}
