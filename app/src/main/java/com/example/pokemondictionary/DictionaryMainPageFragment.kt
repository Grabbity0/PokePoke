package com.example.pokemondictionary

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemondictionary.databinding.FragmentDetailDictionaryBinding
import com.example.pokemondictionary.databinding.FragmentDictionaryMainPageBinding
import com.example.pokemondictionary.viewmodel.DetailsViewModel
import com.example.pokemondictionary.viewmodel.SpeciesViewModel

class DictionaryMainPageFragment : Fragment() {

    private lateinit var binding: FragmentDictionaryMainPageBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var speciesViewModel: SpeciesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDictionaryMainPageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        detailsViewModel =
            ViewModelProvider(requireParentFragment()).get(DetailsViewModel::class.java)

        speciesViewModel =
            ViewModelProvider(requireParentFragment()).get(SpeciesViewModel::class.java)

        detailsViewModel.selectedPokemonDetails.observe(viewLifecycleOwner) { pokemonDetails ->

            println(pokemonDetails)

            Glide.with(binding.root.context)
                .load(pokemonDetails.sprites?.other?.officialArtwork?.frontDefault)
                .into(binding.ivMaindictionaryCWPokemonImage)

            with(binding) {
                val pkno = getString(R.string.dictionary_no_prefix, pokemonDetails.id)
                tvMaindictionaryCWNo.text = pkno


                val colorsArray: Array<String> = resources.getStringArray(R.array.background_color)

                val backgroundColor: Int =
                    when (pokemonDetails.types?.get(0)?.type?.name) {
                        colorsArray[0] -> R.color.type_normal
                        colorsArray[1] -> R.color.type_fire
                        colorsArray[2] -> R.color.type_water
                        colorsArray[3] -> R.color.type_grass
                        colorsArray[4] -> R.color.type_electric
                        colorsArray[5] -> R.color.type_ice
                        colorsArray[6] -> R.color.type_fighting
                        colorsArray[7] -> R.color.type_poison
                        colorsArray[8] -> R.color.type_ground
                        colorsArray[9] -> R.color.type_flying
                        colorsArray[10] -> R.color.type_psychic
                        colorsArray[11] -> R.color.type_bug
                        colorsArray[12] -> R.color.type_rock
                        colorsArray[13] -> R.color.type_ghost
                        colorsArray[14] -> R.color.type_dragon
                        colorsArray[15] -> R.color.type_dark
                        colorsArray[16] -> R.color.type_steel
                        colorsArray[17] -> R.color.type_fairy
                        else -> {
                            R.color.black
                        }
                    }

                println(backgroundColor)

                context?.let {
                    ContextCompat.getColor(
                        it, backgroundColor
                    )
                }?.let { vMaindictionaryCWBackground.setBackgroundColor(it) }

                tvMaindictionaryCMName.text = pokemonDetails.name

                tvMaindictionaryCMType1.text = pokemonDetails.types?.get(0)?.type?.name
                if ((pokemonDetails.types?.size ?: 0) >= 2) {
                    tvMaindictionaryCMType2.text = pokemonDetails.types?.get(1)?.type?.name
                } else {
                    tvMaindictionaryCMType2.text = getString(R.string.blank_type)
                }

                tvMaindictionaryCDSeparate1.text = getString(R.string.separation_line)
                tvMaindictionaryCDSeparate2.text = getString(R.string.separation_line)
                val height = pokemonDetails.height?.toFloat()?.div(10)
                val formattedHeight = "%.1f".format(height)
                tvMaindictionaryCDHeight.text = getString(R.string.height, formattedHeight)
                val weight = pokemonDetails.weight?.toFloat()?.div(10)
                val formattedWeight = "%.1f".format(weight)
                tvMaindictionaryCDWeight.text = getString(R.string.weight, formattedWeight)
            }
        }
        speciesViewModel.selectedPokemonSpecies.observe(viewLifecycleOwner) { pokemonSpecies ->

            with(binding) {
                tvMaindictionaryCDSpecies.text =
                    pokemonSpecies.genera.find { it.language.name == "en" }?.genus

                val flavorEntries =
                    pokemonSpecies.flavorTextEntries.filter { it.language.name == "en" }
                val firstFlavor = flavorEntries.getOrNull(0)?.flavorText
                var secondFlavor: String? = null
                for (entry in flavorEntries) {
                    val flavorText = entry.flavorText
                    if (flavorText != firstFlavor) {
                        secondFlavor = flavorText
                    }

                }

                val modifiedFirstFlavor = firstFlavor?.replace("\n", " ")
                val modifiedSecondFlavor = secondFlavor?.replace("\n", " ")

                val concatFlavor = "${modifiedFirstFlavor}\n${modifiedSecondFlavor}"
                tvMaindictionaryCDFlavor.text = concatFlavor

                tvMaindictionaryCDGender.text = getString(R.string.gender)
                val genderRate = pokemonSpecies.genderRate
                val female = "%.1f".format((genderRate / 8.0 * 100))
                val male = "%.1f".format(100 - female.toFloat())
                tvMaindictionaryCDMale.text = getString(R.string.gender_value, male)
                tvMaindictionaryCDFemale.text = getString(R.string.gender_value, female)
            }
        }

        return binding.root
    }

}