package com.example.pokemondictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemondictionary.databinding.MainItemBinding
import com.example.pokemondictionary.dto.PokemonSimpleDTO

class RecyclerViewMainAdapter(private val itemClickListener: MainItemClickListener) : ListAdapter<PokemonSimpleDTO, RecyclerViewMainAdapter.MainViewHolder>(MainDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = DataBindingUtil.inflate<MainItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.main_item,
            parent,
            false
        )

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val pokemonData = getItem(position)

        holder.bind(pokemonData)

    }

    class ItemClickListener(val clickListener: (mainItemId: PokemonSimpleDTO) -> Unit): MainItemClickListener {
        override fun onItemClicked(item: PokemonSimpleDTO) = clickListener(item)
    }

    inner class MainViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonSimpleDTO) {

            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .into(binding.ivMainitemPokemon)

            with(binding) {
                mainItem = item
                clickListener = itemClickListener

                executePendingBindings()
            }

        }
    }

    object MainDiffUtilCallback : DiffUtil.ItemCallback<PokemonSimpleDTO>() {

        override fun areItemsTheSame(
            oldItem: PokemonSimpleDTO,
            newItem: PokemonSimpleDTO
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PokemonSimpleDTO,
            newItem: PokemonSimpleDTO
        ): Boolean {
            return oldItem == newItem
        }


    }
}