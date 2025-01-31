package com.globant.rickmorty.data.dto

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterInfo>,
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String,
)

data class CharacterInfo(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: String,
)