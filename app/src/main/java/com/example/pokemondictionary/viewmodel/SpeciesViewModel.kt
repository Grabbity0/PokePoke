package com.example.pokemondictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemondictionary.getapi.PokemonSpecies

class SpeciesViewModel : ViewModel() {
    private val _selectedPokemonSpecies = MutableLiveData<PokemonSpecies>()
    val selectedPokemonSpecies: LiveData<PokemonSpecies> get() = _selectedPokemonSpecies

    fun setSelectedPokemonSpecies(item: PokemonSpecies){
        _selectedPokemonSpecies.value = item
    }
}