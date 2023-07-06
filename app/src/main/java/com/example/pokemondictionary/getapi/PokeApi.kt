package com.example.pokemondictionary.getapi

import com.example.pokemondictionary.getapi.PokemonDetails
import com.example.pokemondictionary.getapi.PokemonSimpleDetails
import com.example.pokemondictionary.getapi.PokemonList
import com.example.pokemondictionary.getapi.PokemonSpecies
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon?limit=120")
    suspend fun getAllPokemonList(): PokemonList // 포켓몬 id값 추출용

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Any): PokemonDetails // 필요 데이터 리스트

    @GET("{id}")
    suspend fun getPokemonSimpleDetails(@Path("id") id: Int): PokemonSimpleDetails // 필요 데이터 리스트

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpecies

    @GET("evolution-chain/{id}")
    suspend fun getPokemonEvolutionChain(@Path("id") id: Int): PokemonEvolutionChain
}