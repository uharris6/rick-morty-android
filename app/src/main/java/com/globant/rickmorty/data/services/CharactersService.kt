package com.globant.rickmorty.data.services

import com.globant.rickmorty.data.dto.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET(CHARACTER_URL)
    suspend fun getCharacters(@Query(PAGE) page: Int = 1): CharacterResponse

    companion object {
        private const val CHARACTER_URL = "character"
        private const val PAGE = "page"
    }
}