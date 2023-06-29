package com.example.pokemondictionary

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemondictionary.SplashLoadingActivity.Companion.pokemonDataBase
import com.example.pokemondictionary.databinding.ActivityMainBinding
import com.example.pokemondictionary.dto.PokemonSimpleDTO
import com.example.pokemondictionary.dto.convert
import com.example.pokemondictionary.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class MainActivity : AppCompatActivity(), MainItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewMainAdapter
    private lateinit var defaultList: List<PokemonSimpleDTO>
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.svMainSearch.isSubmitButtonEnabled = true

        binding.rvMainPokemonList.layoutManager = LinearLayoutManager(this)

        adapter = RecyclerViewMainAdapter(this)
        binding.rvMainPokemonList.adapter = adapter

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            defaultList = mainDataSet()
            withContext(Dispatchers.Main) {
                adapter.submitList(defaultList)
                searchView(binding)
            }
        }


    }

    private suspend fun mainDataSet(): List<PokemonSimpleDTO> {
        val urlPath = pokemonDataBase
        Log.d("ddd", urlPath.toString())
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(PokeApi::class.java)

        val dataList: MutableList<PokemonSimpleDTO> = mutableListOf()

        try {
            for (item in urlPath.results) {
                val url = URL(item.url)
                val path = url.path
                val id = path.split("/")
                Log.d("MainActivity", "Results count: ${urlPath.results.size}")
                val mainItemDetails = api.getPokemonSimpleDetails(id[4].toInt())
                val dataChanger = convert(mainItemDetails)
                dataList.add(dataChanger)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(ContentValues.TAG, "Error occurred: ${e.message}")
        }

        return dataList
    }

    private fun searchView(binding: ActivityMainBinding) {
        binding.svMainSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchedList: MutableList<PokemonSimpleDTO> = mutableListOf()
                if (query.isNullOrBlank()) {
                    adapter.submitList(defaultList)
                } else {
                    val regex = Regex("$query")

                    for (search in defaultList) {
                        val result = search.name?.let { regex.containsMatchIn(it) } == true
                        if (result) {
                            searchedList.add(search)
                        }
                    }
                    adapter.submitList(searchedList)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println(newText)
                return false
            }

        })
    }

    override fun onItemClicked(item: PokemonSimpleDTO) {
        val fragment = DictionaryDetailFragment()

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
        fragmentTransaction.replace(R.id.fl_main_fragmentcontainer, fragment)
        fragmentTransaction.commit()

        binding.clMainParentLayout.isEnabled = false

        item.id?.let { sharedViewModel.setSelectedItemId(it.toInt()) }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_main_fragmentcontainer, fragment)
            .commit()

    }

}