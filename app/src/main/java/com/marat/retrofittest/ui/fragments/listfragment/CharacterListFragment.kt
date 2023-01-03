package com.marat.retrofittest.ui.fragments.listfragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.marat.retrofittest.R
import com.marat.retrofittest.databinding.FragmentCharacterListBinding
import com.marat.retrofittest.ui.base.BaseFragment
import com.marat.retrofittest.ui.fragments.detailfragment.DetailInformationFragment
import com.marat.retrofittest.ui.fragments.listfragment.adapter.CharactersLoadStateAdapter
import com.marat.retrofittest.ui.fragments.listfragment.adapter.RikAdapter
import com.marat.retrofittest.ui.fragments.searchfragment.SearchFragment
import com.turtleteam.domain.model.Result
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : BaseFragment<FragmentCharacterListBinding>(),
    RikAdapter.CharacterListListener {

    private val viewModel by viewModel<CharactersListViewModel>()
    private val charactersAdapter = RikAdapter(this)

    companion object {
        const val ITEM_ARGUMENT = "argument"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listToolbar.inflateMenu(R.menu.toolbar_menu)
        binding.listToolbar.menu.findItem(R.id.search_button_menu).setShowAsActionFlags(1)
            .setOnMenuItemClickListener {
                parentFragmentManager.beginTransaction().addToBackStack(null)
                    .setCustomAnimations(R.animator.to_left_in,
                        R.animator.to_left_out,
                        R.animator.to_right_in,
                        R.animator.to_right_out)
                    .replace(R.id.fragment_container_view, SearchFragment()).commit()
                true
            }

        initCharactersList()

        lifecycleScope.launch {
            viewModel.characterList.collect {
                charactersAdapter.submitData(it)
            }
        }
    }

    private fun initCharactersList() {

        val footer = CharactersLoadStateAdapter { charactersAdapter.retry() }
        val mlayoutManager = GridLayoutManager(this.context, 4)

        charactersAdapter.addLoadStateListener {
            if (charactersAdapter.itemCount < 1) {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is LoadState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is LoadState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.stateview.retryView.visibility = View.VISIBLE
                        binding.stateview.retryBtn.setOnClickListener {
                            charactersAdapter.retry()
                            binding.stateview.retryView.visibility = View.INVISIBLE
                        }
                    }
                }
            } else binding.progressBar.visibility = View.GONE
        }

        binding.rcView.apply {
            layoutManager = mlayoutManager
            adapter = charactersAdapter.withLoadStateFooter(footer)
            postponeEnterTransition()
            this.doOnPreDraw { startPostponedEnterTransition() }
        }
        val orientation = requireActivity().resources.configuration.orientation
        mlayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        if (position == charactersAdapter.itemCount && footer.itemCount > 0) {
                            4
                        } else {
                            1
                        }
                    }
                    Configuration.ORIENTATION_PORTRAIT -> {
                        if (position == charactersAdapter.itemCount && footer.itemCount > 0) {
                            4
                        } else {
                            2
                        }
                    }
                    else -> {
                        if (position == charactersAdapter.itemCount && footer.itemCount > 0) {
                            4
                        } else {
                            2
                        }
                    }
                }
            }
        }
    }

    override fun onCharacterClick(item: Result, img: View) {
        parentFragmentManager.beginTransaction().addToBackStack("listfragment")
            .addSharedElement(img, item.id.toString()).add(R.id.fragment_container_view,
                DetailInformationFragment::class.java,
                bundleOf(ITEM_ARGUMENT to item)).hide(this).commit()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentCharacterListBinding =
        FragmentCharacterListBinding.inflate(inflater, container, false)
}