package com.marat.retrofittest.ui.fragments.listfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.databinding.ItemCharacterBinding
import com.marat.retrofittest.ui.base.Diffutils

class RikAdapter(private var listener: CharacterListListener) : PagingDataAdapter<Result, RikAdapter.RikHolder>(Diffutils()) {

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
        getItem(position)?.let { holder.bind(it, listener) }
    }
}