package com.example.pokemondictionary.getapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService{

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: PokeApi = retrofit.create(PokeApi::class.java)


    suspend fun getAllPokemonList(): PokemonList? {
        val pokemonList: PokemonList? = try {
            api.getAllPokemonList()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        println(pokemonList)
        return pokemonList
    }

    suspend fun getPokemonDetails(pokemonId: Any) : PokemonDetails?{
        val pokemonDetails: PokemonDetails? = try {
            api.getPokemonDetails(pokemonId)

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        return pokemonDetails

    }

    suspend fun getSimplePokemonDetails(pokemonId: Int) : PokemonSimpleDetails?{
        val pokemonSimpleDetails: PokemonSimpleDetails? = try{
            api.getPokemonSimpleDetails(pokemonId)

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        return pokemonSimpleDetails
    }

    suspend fun getPokemonSpecies(pokemonId: Int) : PokemonSpecies?{
        val pokemonSpecies: PokemonSpecies? = try{
            api.getPokemonSpecies(pokemonId)
        } catch( e: Exception){
            e.printStackTrace()
            null
        }

        return pokemonSpecies
    }

    suspend fun getPokemonEvolutionChain(evolutionId: Int) : PokemonEvolutionChain?{
        val pokemonEvolutionChain: PokemonEvolutionChain? = try{
            api.getPokemonEvolutionChain(evolutionId)
        } catch( e: Exception){
            e.printStackTrace()
            null
        }

        return pokemonEvolutionChain
    }
}



