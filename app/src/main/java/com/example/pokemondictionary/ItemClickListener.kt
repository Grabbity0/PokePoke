package com.example.pokemondictionary

import com.example.pokemondictionary.dto.PokemonSimpleDTO

interface MainItemClickListener {
    fun onItemClicked(item: PokemonSimpleDTO)
}