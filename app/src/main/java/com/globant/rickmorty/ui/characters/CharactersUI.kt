package com.globant.rickmorty.ui.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.globant.rickmorty.data.dto.CharacterInfo
import com.globant.rickmorty.ui.theme.RickMortyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterUi(
    viewModel: CharactersViewModel = koinViewModel()
) {
    val charactersState by viewModel.characterState.collectAsStateWithLifecycle()

    CharactersContent(charactersState = charactersState)
}

@Composable
fun CharactersContent(charactersState: CharactersState) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when (charactersState) {
                CharactersState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is CharactersState.ShowCharacters -> {
                    if (charactersState.error) {
                        Text(
                            text = "Sorry, we found and error",
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(horizontal = 16.dp),
                        )
                    } else {
                        CharactersList(characterInfoList = charactersState.charactersList)
                    }
                }
            }
        }
    }
}

@Composable
fun CharactersList(
    characterInfoList: List<CharacterInfo>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        items(characterInfoList, key = { it.id }) { characterInfo ->
            CharacterItem(characterInfo = characterInfo)
        }
    }
}

@Composable
fun CharacterItem(characterInfo: CharacterInfo) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        AsyncImage(
            model = characterInfo.image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(text = characterInfo.name)
        Text(text = characterInfo.species)
    }
}

@Composable
@PreviewLightDark
private fun CharacterUiLoadingPreview() {
    RickMortyTheme {
        CharactersContent(
            charactersState = CharactersState.Loading
        )
    }
}

@Composable
@PreviewLightDark
private fun CharacterUiListPreview() {
    RickMortyTheme {
        CharactersContent(
            charactersState = CharactersState.ShowCharacters(
                listOf(
                    CharacterInfo(
                        id = 1,
                        name = "Rick Sanchez",
                        status = "Alive",
                        species = "Human",
                        type = "",
                        gender = "Male",
                        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        created = "2017-11-04T18:48:46.250Z"
                    ),
                    CharacterInfo(
                        id = 2,
                        name = "Rick Sanchez",
                        status = "Alive",
                        species = "Human",
                        type = "",
                        gender = "Male",
                        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        created = "2017-11-04T18:48:46.250Z"
                    ),
                    CharacterInfo(
                        id = 3,
                        name = "Rick Sanchez",
                        status = "Alive",
                        species = "Human",
                        type = "",
                        gender = "Male",
                        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        created = "2017-11-04T18:48:46.250Z"
                    )
                )
            )
        )
    }
}
