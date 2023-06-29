package com.example.pokemondictionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemondictionary.dto.PokemonSimpleDTO

class MainClickViewModel() : ViewModel() {
    private val sharedPokemonList = MutableLiveData<PokemonSimpleDTO>()

    fun getMainData(): MutableLiveData<PokemonSimpleDTO> {
        return sharedPokemonList
    }

    fun setMainData(data: PokemonSimpleDTO){
        sharedPokemonList.value = data
    }


}