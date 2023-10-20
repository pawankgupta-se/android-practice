package com.gowittgroup.commonui.base

interface IReducer<STATE, T: Any> {
    fun reduce(result: Result<T>, state: STATE): STATE
}
