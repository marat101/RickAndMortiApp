package com.marat.retrofittest.listfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.marat.retrofittest.databinding.ItemCharacterBinding
import com.marat.retrofittest.model.Character
import com.marat.retrofittest.model.Result
import retrofit2.Response

class RikAdapter(private val onClick: (item: Result) -> Unit) :
    RecyclerView.Adapter<RikAdapter.RikHolder>() {
    private var characterList = emptyList<Result>()

    class RikHolder(
        private val binding: ItemCharacterBinding,
        private val onClick: (item: Result) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result) = with(binding) {
            characterName.text = item.name
            characterImg.load(item.image)
            itemView.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RikHolder {
        val inflater =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RikHolder(inflater, onClick)
    }

    override fun onBindViewHolder(holder: RikHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Result>) {
        characterList = list
        notifyDataSetChanged()
    }
}