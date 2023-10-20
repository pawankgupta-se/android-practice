package com.gowittgroup.mvitemplate.home

import com.gowittgroup.commonui.base.BaseViewModel
import com.gowittgroup.data.datasources.CharactersDataSource
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val dataSource: CharactersDataSource): BaseViewModel<HomeIntent, HomeAction, HomeState>() {

    override fun intentToAction(intent: HomeIntent): HomeAction {
        return when(intent){
            is HomeIntent.LoadAllCharacters -> HomeAction.AllCharacters
            is HomeIntent.SearchCharacter -> HomeAction.SearchCharacter(intent.name)
            is HomeIntent.ClearSearch -> HomeAction.AllCharacters
        }
    }

    override fun handleAction(action: HomeAction) {
        lunchOnUi {
            when(action){
                is HomeAction.AllCharacters -> {
                    dataSource.getAllCharacters().collect{
                        mState.postValue(it.reduce())
                    }
                }
                is HomeAction.SearchCharacter -> {
                    dataSource.searchCharacters(action.name).collect{
                        mState.postValue(it.reduce(true))
                    }
                }
            }
        }
    }

}
