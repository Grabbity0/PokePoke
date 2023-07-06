package com.example.pokemondictionary.getapi

import com.google.gson.annotations.SerializedName

data class PokemonEvolutionChain(
    @SerializedName("chain")
    val chain: Chain
) {
    data class Chain(
        @SerializedName("evolves_to")
        val evolvesTO: List<EvolvesTO>?,
        @SerializedName("species")
        val species: Species
    ){
        data class EvolvesTO(
            @SerializedName("evolves_to")
            val evolvesTO: List<EvolvesTO>?,
            @SerializedName("species")
            val species: Species
        )

        data class Species(
            @SerializedName("name")
            val name: String
        )
    }
}
