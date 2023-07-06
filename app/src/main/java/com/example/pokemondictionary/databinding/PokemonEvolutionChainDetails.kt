package com.example.pokemondictionary.databinding

import com.example.pokemondictionary.getapi.PokemonDetails

data class PokemonEvolutionChainDetails(
    val evolution: List<Evolution>
) {
    data class Evolution(
        val imageUrl: String?,
        val number: String?,
        val name: String?
    )
}

fun convert(instance: PokemonDetails): PokemonEvolutionChainDetails.Evolution {
    val imageUrl = instance.sprites?.other?.officialArtwork?.frontDefault
    val number = instance.id.toString()
    val name = instance.name

    return PokemonEvolutionChainDetails.Evolution(imageUrl, number, name)
}