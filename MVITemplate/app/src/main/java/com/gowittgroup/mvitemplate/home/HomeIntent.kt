package com.gowittgroup.mvitemplate.home

import com.gowittgroup.commonui.base.ViewIntent


sealed class HomeIntent : ViewIntent {
    object LoadAllCharacters : HomeIntent()
    data class SearchCharacter(val name: String): HomeIntent()
    object ClearSearch: HomeIntent()
}
