package com.marat.retrofittest.ui.fragments.listfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marat.retrofittest.databinding.StateErrorBinding
import com.marat.retrofittest.databinding.StateLoadingBinding

class CharactersLoadStateAdapter(private val onRetry: () -> Unit) :
    LoadStateAdapter<RecyclerView.ViewHolder>() {

    enum class State(val state: Int) { LOADING(0), ERROR(1) }

    override fun getStateViewType(loadState: LoadState): Int = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        is LoadState.Loading -> State.LOADING.state
        is LoadState.Error -> State.ERROR.state
        else -> {
            State.LOADING.state
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): RecyclerView.ViewHolder {
        return when (loadState) {
            LoadState.Loading -> LoadingViewHolder(StateLoadingBinding.inflate(LayoutInflater.from(
                parent.context), parent, false))
            is LoadState.Error -> {
                ErrorViewHolder(StateErrorBinding.inflate(LayoutInflater.from(
                    parent.context), parent, false), onRetry)
            }
            else
            -> {
                ErrorViewHolder(StateErrorBinding.inflate(LayoutInflater.from(
                    parent.context), parent, false), onRetry)
            }
        }
    }

    class ErrorViewHolder(
        binding: StateErrorBinding,
        private val onRetry: () -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryBtn.setOnClickListener {
                onRetry()
            }
        }
    }

    class LoadingViewHolder(binding: StateLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)
}