package com.turtleteam.ui.fragments.favoritesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import com.turtleteam.domain.model.Result
import com.turtleteam.domain.model.network.FavoritesResult
import com.turtleteam.ui.R
import com.turtleteam.ui.base.BaseFragment
import com.turtleteam.ui.databinding.FragmentFavoritesBinding
import com.turtleteam.ui.fragments.detailfragment.DetailInformationFragment
import com.turtleteam.ui.fragments.favoritesfragment.adapter.FavoritesAdapter
import com.turtleteam.ui.fragments.listfragment.CharacterListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(),
    FavoritesAdapter.FavoriteCharacterListListener {

    private val viewModel by viewModel<FavoritesViewModel>()
    private val adapter = FavoritesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favRcView.adapter = adapter
        setFragmentResultListener("refresh") { _, _ ->
            viewModel.getList()
        }
        viewModel.favorites.observe(viewLifecycleOwner) {
            handleResults(it)
        }
        binding.stateview.retryBtn.setOnClickListener {
            binding.stateview.retryView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getList()
        }
        viewModel.getList()
    }

    private fun handleResults(result: FavoritesResult<List<Result>>) {
        when (result) {
            FavoritesResult.ConnectionError,
            is FavoritesResult.Error -> {
                binding.stateview.retryView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
            FavoritesResult.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is FavoritesResult.Success -> {
                binding.progressBar.visibility = View.GONE
                adapter.submitList(result.value)
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentFavoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false)

    override fun onCharacterClick(item: Result, img: View) {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.to_left_in,
                R.animator.to_left_out,
                R.animator.to_right_in,
                R.animator.to_right_out
            ).add(
                R.id.fragment_container_view,
                DetailInformationFragment::class.java,
                bundleOf(CharacterListFragment.ITEM_ARGUMENT to item)
            )
            .addToBackStack(this::class.java.name)
            .hide(parentFragmentManager.findFragmentByTag("base")!!)
            .commit()
    }
}