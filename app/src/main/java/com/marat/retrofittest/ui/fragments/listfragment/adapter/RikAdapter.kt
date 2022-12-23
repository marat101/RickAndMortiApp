package com.marat.retrofittest.ui.fragments.listfragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.databinding.ItemCharacterBinding

class RikAdapter(private var listener: CharacterListListener) :
    RecyclerView.Adapter<RikAdapter.RikHolder>() {
    private var characterList = mutableListOf<Result>()

    interface CharacterListListener{
        fun onCharacterClick(item: Result, img: View)
    }

    class RikHolder(
        private val binding: ItemCharacterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result, listener: CharacterListListener) = with(binding) {
            characterName.text = item.name
            characterImg.load(item.image)
            characterImg.transitionName = item.id.toString()
            itemView.setOnClickListener { listener.onCharacterClick(item, characterImg) }
            binding.characterImg.transitionName = item.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RikHolder {
        val inflater = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RikHolder(inflater)
    }

    override fun onBindViewHolder(holder: RikHolder, position: Int) {
        holder.bind(characterList[position], listener)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Result>) {
        characterList.clear()
        characterList.addAll(list)
        notifyDataSetChanged()
    }
}