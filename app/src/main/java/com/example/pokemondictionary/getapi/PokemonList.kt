package com.example.pokemondictionary.getapi

import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("count")
    val count : String,
    @SerializedName("next")
    val next : String?,
    @SerializedName("previous")
    val previous : String?,
    @SerializedName("results")
    val results : List<Results>
){
    data class Results(
        @SerializedName("name")
        val name : String,
        @SerializedName("url")
        val url : String
    )
}

