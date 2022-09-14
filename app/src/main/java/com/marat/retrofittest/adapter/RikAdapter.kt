package com.marat.retrofittest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.marat.retrofittest.Character
import com.marat.retrofittest.Constants
import com.marat.retrofittest.databinding.ItemCharacterBinding

class RikAdapter() : RecyclerView.Adapter<RikAdapter.RikHolder>() {
    private val characterList = mutableListOf<Character>()

    class RikHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character) = with(binding) {
            characterName.text = item.name
            characterImg.load(item.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RikHolder {
        val inflater = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RikHolder(inflater)
    }

    override fun onBindViewHolder(holder: RikHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
    fun addTestList(list: List<Character>){
        characterList.addAll(list)
    }
}