package com.gowittgroup.data.datasources

import com.gowittgroup.data.applyCommonSideEffects
import com.gowittgroup.data.models.CallErrors
import com.gowittgroup.wrapper.services.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.gowittgroup.data.models.Result
import com.gowittgroup.wrapper.models.toModel
import com.gowittgroup.wrapper.models.Persona

class CharactersDataSourceImpl(private val api: ApiService) : CharactersDataSource {
    override fun getAllCharacters(): Flow<Result<List<Persona>>> = flow {
        api.getAllCharacters().run {
            if (this.isSuccessful) {
                this.body()?.let { emit(Result.Success(it.results.toModel())) } ?: emit(Result.Error(CallErrors.ErrorEmptyData))
            }
        }
    }.applyCommonSideEffects()

    override fun searchCharacters(name: String): Flow<Result<List<Persona>>>  = flow {
        api.searchCharacterByName(name).run {
            if (this.isSuccessful) {
                this.body()?.let { emit(Result.Success(it.results.toModel())) } ?: emit(Result.Error(CallErrors.ErrorEmptyData))
            } else {
                emit(Result.Error(CallErrors.ErrorServer))
            }
        }
    }.applyCommonSideEffects()
}
