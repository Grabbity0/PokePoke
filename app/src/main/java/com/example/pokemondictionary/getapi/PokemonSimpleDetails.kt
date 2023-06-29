package com.example.pokemondictionary.getapi

import com.google.gson.annotations.SerializedName

data class PokemonSimpleDetails(
    @SerializedName("id")
    val id: Int?, // 포켓몬 ID값

    @SerializedName("name")
    val name: String?, // 포켓몬 이름

    @SerializedName("sprites")
    val sprites: Sprite?,

    @SerializedName("types")
    val types: List<PokemonTypes?>?
){
    data class Sprite(
        @SerializedName("front_default")
        val frontDefault: String?
    )

    data class PokemonTypes(
        @SerializedName("slot")
        val slot: Int?,
        @SerializedName("type")
        val type: Type?
    ) {
        data class Type(
            @SerializedName("name")
            val name: String?,
            @SerializedName("url")
            val url: String?
        )
    }
}
