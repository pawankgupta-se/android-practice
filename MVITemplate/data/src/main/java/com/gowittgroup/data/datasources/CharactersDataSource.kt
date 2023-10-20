package com.gowittgroup.data.datasources

import kotlinx.coroutines.flow.Flow
import com.gowittgroup.data.models.Result
import com.gowittgroup.wrapper.models.Persona

interface CharactersDataSource {
    fun getAllCharacters(): Flow<Result<List<Persona>>>
    fun searchCharacters(name: String): Flow<Result<List<Persona>>>
}
