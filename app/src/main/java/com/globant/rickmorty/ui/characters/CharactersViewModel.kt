package com.globant.rickmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.rickmorty.data.CharactersRepo
import com.globant.rickmorty.data.dto.CharacterInfo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class CharactersViewModel(
    charactersRepo: CharactersRepo
) : ViewModel() {

    val characterState = charactersRepo
        .getCharacters()
        .onStart { CharactersState.Loading }
        .map { CharactersState.ShowCharacters(it) }
        .catch { emit(CharactersState.ShowCharacters(listOf(), true)) }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(), CharactersState.Loading
        )
}

sealed class CharactersState {
    data object Loading : CharactersState()

    data class ShowCharacters(val charactersList: List<CharacterInfo>, val error: Boolean = false) :
        CharactersState()

}