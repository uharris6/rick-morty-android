package com.globant.rickmorty.di

import com.globant.rickmorty.ui.characters.CharactersViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModeModule = module {
    viewModelOf(::CharactersViewModel)
}