package com.globant.rickmorty.data

import com.globant.rickmorty.data.dto.CharacterInfo
import com.globant.rickmorty.data.services.CharactersService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharactersRepo(
    private val characterService: CharactersService,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getCharacters(page: Int = 1): Flow<List<CharacterInfo>> = flow {
        try {
            emit(characterService.getCharacters(page).results)
        } catch (e: Exception) {
            throw e
        }
    }.flowOn(ioDispatcher)
}