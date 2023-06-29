package com.example.pokemondictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemondictionary.getapi.PokemonDetails

class DetailsViewModel : ViewModel(){
    private val _selectedPokemonDetails = MutableLiveData<PokemonDetails>()
    val selectedPokemonDetails: LiveData<PokemonDetails> get() = _selectedPokemonDetails

    fun setSelectedPokemonDetails(item: PokemonDetails){
        _selectedPokemonDetails.value = item
    }
}