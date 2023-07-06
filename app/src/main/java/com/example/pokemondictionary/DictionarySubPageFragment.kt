package com.example.pokemondictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokemondictionary.databinding.*
import com.example.pokemondictionary.getapi.ApiService
import com.example.pokemondictionary.getapi.PokemonDetails
import com.example.pokemondictionary.getapi.PokemonEvolutionChain
import com.example.pokemondictionary.viewmodel.DetailsViewModel
import com.example.pokemondictionary.viewmodel.EvolutionViewModel
import com.example.pokemondictionary.viewmodel.ScrollbarViewModel
import com.example.pokemondictionary.viewmodel.SpeciesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictionarySubPageFragment : Fragment() {

    private lateinit var bindingSubPage: FragmentDictionarySubPageBinding
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

        bindingSubPage = FragmentDictionarySubPageBinding.inflate(inflater, container, false)
        bindingSubPage.lifecycleOwner = viewLifecycleOwner


        detailsViewModel =
            ViewModelProvider(requireParentFragment()).get(DetailsViewModel::class.java)

        detailsViewModel.selectedPokemonDetails.observe(viewLifecycleOwner) { pokemonDetails ->
            val stats: PokemonStatsProgressBar
            val baseStats = pokemonDetails.stats!!.map { stat -> stat?.baseStat }
            stats = PokemonStatsProgressBar(
                hp = baseStats[0],
                attack = baseStats[1],
                defense = baseStats[2],
                specialAttack = baseStats[3],
                specialDefense = baseStats[4],
                speed = baseStats[5],
                total = baseStats.reduce { acc, value -> acc?.plus((value ?: 0)) }
            )
            bindingSubPage.progressbar = stats

        }

        speciesViewModel =
            ViewModelProvider(requireParentFragment()).get(SpeciesViewModel::class.java)

        evolutionViewModel =
            ViewModelProvider(requireParentFragment()).get(EvolutionViewModel::class.java)

        scrollbarViewModel =
            ViewModelProvider(requireParentFragment()).get(ScrollbarViewModel::class.java)

        evolutionViewModel.selectedPokemonEvolution.observe(viewLifecycleOwner) { pokemonEvolutionChain ->
            CoroutineScope(Dispatchers.IO).launch {
                val chain = pokemonEvolutionChain.chain
                val first = chain.species.name
                val second = chain.evolvesTO?.getOrNull(0)?.species?.name
                val third = chain.evolvesTO?.getOrNull(0)?.evolvesTO?.getOrNull(0)?.species?.name

                val evolutionForm: PokemonEvolutionChainDetails
                val evolutionList = mutableListOf<PokemonEvolutionChainDetails.Evolution>()

                ApiService().getPokemonDetails(first)?.let { pokemonDetails ->
                    val evolution = convert(pokemonDetails)
                    evolutionList.add(evolution)
                }

                second?.let { secondName ->
                    ApiService().getPokemonDetails(secondName)?.let { pokemonDetails ->
                        val evolution = convert(pokemonDetails)
                        evolutionList.add(evolution)
                    }
                }

                third?.let { thirdName ->
                    ApiService().getPokemonDetails(thirdName)?.let { pokemonDetails ->
                        val evolution = convert(pokemonDetails)
                        evolutionList.add(evolution)
                    }
                }

                evolutionForm = PokemonEvolutionChainDetails(evolutionList)

                withContext(Dispatchers.Main) {
                    with(bindingSubPage) {
                        evolution = evolutionForm

                        Glide.with(root.context)
                            .load(evolutionForm.evolution[0].imageUrl)
                            .into(ivEvolutionChainItemPokemon1)

                        if (evolutionForm.evolution.size > 1) {
                            Glide.with(root.context)
                                .load(evolutionForm.evolution[1].imageUrl)
                                .into(ivEvolutionChainItemPokemon2)
                        }
                        if (evolutionForm.evolution.size > 2) {
                            Glide.with(root.context)
                                .load(evolutionForm.evolution[2].imageUrl)
                                .into(ivEvolutionChainItemPokemon3)
                        }
                    }


                }
            }
        }

        bindingSubPage.svSubdictionaryScroll.setOnScrollChangeListener{_, _, height, _, _ ->
            scrollbarViewModel.setScrollBarHeight(height)
        }

        scrollbarViewModel.scrollBarHeight.observe(viewLifecycleOwner){height ->
            with(bindingSubPage.svSubdictionaryScroll){
                if(scrollY != height){
                    scrollTo(0, height)
                }
            }
        }


        return bindingSubPage.root
    }

}