package com.example.pokemondictionary.dto

import com.example.pokemondictionary.getapi.PokemonSimpleDetails

data class PokemonSimpleDTO(
    val imageUrl : String?,
    val id : String?,
    val name : String?,
    val typeMain : String?,
    val typeSub : String?
)

fun convert(instance: PokemonSimpleDetails): PokemonSimpleDTO {
    val frontDefault = instance.sprites?.frontDefault
    val id = instance.id.toString()
    val name = instance.name
    val typeMain = instance.types?.get(0)?.type?.name
    val typeSub = if ((instance.types?.size ?: 0) <= 1) {
        null
    } else {
        instance.types?.get(1)?.type?.name
    }

    return PokemonSimpleDTO(frontDefault, id, name, typeMain, typeSub)
}
