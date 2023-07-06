package com.example.pokemondictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokemondictionary.databinding.FragmentDetailDictionaryBinding
import com.example.pokemondictionary.getapi.ApiService
import com.example.pokemondictionary.viewmodel.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictionaryDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailDictionaryBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var speciesViewModel: SpeciesViewModel
    private lateinit var evolutionViewModel: EvolutionViewModel
    private lateinit var scrollbarViewModel: ScrollbarViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailDictionaryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        speciesViewModel = ViewModelProvider(this).get(SpeciesViewModel::class.java)
        evolutionViewModel = ViewModelProvider(this).get(EvolutionViewModel::class.java)
        scrollbarViewModel = ViewModelProvider(this).get(ScrollbarViewModel::class.java)

        sharedViewModel.selectedItemId.observe(viewLifecycleOwner){itemId ->
            CoroutineScope(Dispatchers.IO).launch {
                val item = ApiService().getPokemonDetails(itemId)
                val speciesItem = ApiService().getPokemonSpecies(itemId)

                withContext(Dispatchers.Main){
                    if (item != null) {
                        detailsViewModel.setSelectedPokemonDetails(item)
                    }
                    if (speciesItem != null){
                        speciesViewModel.setSelectedPokemonSpecies(speciesItem)
                    }
                }
            }
        }

        speciesViewModel.selectedPokemonSpecies.observe(viewLifecycleOwner){pokemonSpecies ->
            CoroutineScope(Dispatchers.IO).launch {
                val url = pokemonSpecies.evolutionChain.url
                val pattern = Regex("/(\\d+)/$")
                val matchResult = pattern.find(url)
                val targetPart = matchResult?.groups?.get(1)?.value

                val evolutionData = ApiService().getPokemonEvolutionChain(targetPart!!.toInt())

                withContext(Dispatchers.Main){
                    if(evolutionData != null){
                        evolutionViewModel.setSelectedPokemonEvolution(evolutionData)
                    }
                    println(evolutionData)
                }
            }

        }

        val viewPager = binding.vpDictionaryViewpager
        val adapter = DictionaryPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }


    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            closeFragment()
        }

    }

    private fun closeFragment() {
        val fragmentManager = requireActivity().supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_out_left,R.anim.slide_in_right)
        fragmentTransaction.commit()

        fragmentManager.beginTransaction().remove(this).commit()
    }

}