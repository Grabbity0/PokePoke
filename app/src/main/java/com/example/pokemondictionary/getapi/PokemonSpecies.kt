package com.example.pokemondictionary.getapi

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("evolution_chain")
    val evolutionChain : EvolutionChain,

    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntries>,

    @SerializedName("gender_rate")
    val genderRate: Int,

    @SerializedName("genera")
    val genera: List<Genera>
    ){
    data class EvolutionChain(
        @SerializedName("url")
        val url: String
        )
    data class FlavorTextEntries(
        @SerializedName("flavor_text")
        val flavorText: String,

        @SerializedName("language")
        val language: Language,

        @SerializedName("version")
        val version: Version
    ) {
        data class Version(
            @SerializedName("name")
            val name: String
        )
    }
    data class Genera(
        @SerializedName("genus")
        val genus: String,

        @SerializedName("language")
        val language: Language
    )

    data class Language(
        @SerializedName("name")
        val name: String
    )
}
