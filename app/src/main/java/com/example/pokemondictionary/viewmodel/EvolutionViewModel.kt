package com.example.pokemondictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemondictionary.getapi.PokemonEvolutionChain

class EvolutionViewModel: ViewModel() {
    private val _selectedPokemonEvolution = MutableLiveData<PokemonEvolutionChain>()
    val selectedPokemonEvolution: LiveData<PokemonEvolutionChain> get() = _selectedPokemonEvolution

    fun setSelectedPokemonEvolution(item: PokemonEvolutionChain){
        _selectedPokemonEvolution.value = item
    }
}