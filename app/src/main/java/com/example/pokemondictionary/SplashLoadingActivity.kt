package com.example.pokemondictionary

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pokemondictionary.getapi.ApiService
import com.example.pokemondictionary.getapi.PokemonList
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashLoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_loading)
        CoroutineScope(Dispatchers.IO).launch {
            dataLoadingProgress()
            withContext(Dispatchers.Main) {
                navigateToMain()
            }

        }
    }

    private suspend fun dataLoadingProgress() { //async, retrofit&coroutine
       pokemonDataBase = ApiService().getAllPokemonList()!!
    }

    companion object {
        lateinit var pokemonDataBase : PokemonList
    }

    private fun navigateToMain() { //sync, 화면전환
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}


