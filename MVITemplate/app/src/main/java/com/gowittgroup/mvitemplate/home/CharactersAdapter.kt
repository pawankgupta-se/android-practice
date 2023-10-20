package com.gowittgroup.mvitemplate.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.gowittgroup.mvitemplate.databinding.ItemCharacterBinding
import com.gowittgroup.wrapper.models.Persona


class CharactersAdapter: RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {
    private var charactersList: List<Persona> = mutableListOf()

    class CharactersViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindCharacters(character: Persona){
            with(character){
                binding.name.text = name
                binding.image.load(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemView = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(itemView)
    }

    override fun getItemCount(): Int = charactersList.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
       holder.bindCharacters(charactersList[position])
    }

    fun updateList(charactersList: List<Persona>){
        this.charactersList = charactersList
        notifyDataSetChanged()
    }

}
