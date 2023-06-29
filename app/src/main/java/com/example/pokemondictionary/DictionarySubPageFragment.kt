package com.example.pokemondictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.pokemondictionary.databinding.FragmentDictionarySubPageBinding
import com.example.pokemondictionary.viewmodel.DetailsViewModel

class DictionarySubPageFragment : Fragment() {

    private lateinit var binding: FragmentDictionarySubPageBinding
    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDictionarySubPageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        detailsViewModel = ViewModelProvider(requireParentFragment()).get(DetailsViewModel::class.java)

        detailsViewModel.selectedPokemonDetails.observe(viewLifecycleOwner){ pokemonDetails ->
            println(pokemonDetails)
        }

        return binding.root
    }

}