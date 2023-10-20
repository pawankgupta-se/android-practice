package com.gowittgroup.mvitemplate.home

import com.gowittgroup.wrapper.models.Persona
import com.gowittgroup.data.models.Result

fun Result<List<Persona>>.reduce(isSearchMode: Boolean = false): HomeState {
    return when(this){
        is Result.Success -> if(isSearchMode) HomeState.ResultSearch(data) else HomeState.ResultAllPersona(data)
        is Result.Error -> HomeState.Exception(exception)
        is Result.Loading -> HomeState.Loading
    }
}
