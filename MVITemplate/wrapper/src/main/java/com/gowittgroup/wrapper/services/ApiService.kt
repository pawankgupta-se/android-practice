package com.gowittgroup.wrapper.services

import com.gowittgroup.wrapper.KEY_NAME
import com.gowittgroup.wrapper.PATH_CHARACTER
import com.gowittgroup.wrapper.models.ECharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(PATH_CHARACTER)
    suspend fun getAllCharacters(): Response<ECharacters>

    @GET(PATH_CHARACTER)
    suspend fun searchCharacterByName(@Query(KEY_NAME) name: String):  Response<ECharacters>
}
