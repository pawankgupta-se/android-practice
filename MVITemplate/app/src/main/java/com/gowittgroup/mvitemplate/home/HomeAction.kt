package com.gowittgroup.mvitemplate.home

import com.gowittgroup.commonui.base.ViewAction


sealed class HomeAction: ViewAction {
    data class SearchCharacter(val name: String): HomeAction()
    object AllCharacters: HomeAction()
}
