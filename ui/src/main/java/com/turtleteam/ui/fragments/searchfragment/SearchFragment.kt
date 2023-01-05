package com.turtleteam.ui.fragments.searchfragment

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.turtleteam.domain.model.Result
import com.turtleteam.ui.R
import com.turtleteam.ui.base.BaseFragment
import com.turtleteam.ui.databinding.FragmentSearchBinding
import com.turtleteam.ui.fragments.detailfragment.DetailInformationFragment
import com.turtleteam.ui.fragments.listfragment.CharacterListFragment
import com.turtleteam.ui.fragments.listfragment.adapter.CharactersLoadStateAdapter
import com.turtleteam.ui.fragments.listfragment.adapter.RikAdapter
import com.turtleteam.ui.view.ViewAnimations
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(), RikAdapter.CharacterListListener {

    private val viewModel by viewModel<SearchViewModel>()
    private val charactersAdapter = RikAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.genderMenu.text = viewModel.gender
        binding.statusMenu.text = viewModel.status

        initCharactersList()
        initListeners()
    }

    private fun update() {
        lifecycleScope.launch {
            viewModel.getData().collectLatest {
                charactersAdapter.submitData(it)
            }
        }
    }

    private fun showPopup(view: TextView, menu: Int) {
        val popup = PopupMenu(this.context, view)
        popup.inflate(menu)
        popup.setOnMenuItemClickListener {
            if (menu == R.menu.gender_menu) {
                viewModel.gender = it.title.toString()
                binding.clearGenderFilter.visibility = View.VISIBLE
            } else {
                viewModel.status = it.title.toString()
                binding.clearStatusFilter.visibility = View.VISIBLE
            }
            view.text = it.title
            update()
            true
        }
        popup.show()
    }

    @SuppressLint("SetTextI18n")
    private fun initListeners() {
        binding.apply {
            if (genderMenu.text != "select") clearGenderFilter.visibility = View.VISIBLE
            if (statusMenu.text != "select") clearStatusFilter.visibility = View.VISIBLE
            filterButton.setOnClickListener {
                if (filterLayout.isInvisible) {
                    filterButton.setImageResource(R.drawable.ic_up)
                    ViewAnimations.showFilterView(filterLayout)
                    genderMenu.setOnClickListener {
                        showPopup(genderMenu, R.menu.gender_menu)
                    }
                    statusMenu.setOnClickListener {
                        showPopup(statusMenu, R.menu.status_menu)
                    }
                } else {
                    filterButton.setImageResource(R.drawable.ic_filter)
                    ViewAnimations.hideFilterView(filterLayout)
                }
            }

            searchText.doAfterTextChanged {
                if (filterLayout.isVisible) {
                    ViewAnimations.hideFilterView(filterLayout)
                    filterButton.setImageResource(R.drawable.ic_filter)
                }
                lifecycleScope.launch {
                    viewModel.name = it.toString()
                    update()
                }
            }
            clearGenderFilter.setOnClickListener {
                viewModel.gender = "select"
                genderMenu.text = "select"
                update()
                it.visibility = View.INVISIBLE
            }
            clearStatusFilter.setOnClickListener {
                viewModel.status = "select"
                statusMenu.text = "select"
                update()
                it.visibility = View.INVISIBLE
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
                        binding.retryView.visibility = View.GONE
                    }
                    is LoadState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is LoadState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        if (charactersAdapter.itemCount == 0) {
                            binding.retryView.visibility = View.VISIBLE
                            binding.retryBtn.setOnClickListener {
                                charactersAdapter.retry()
                                binding.retryView.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            } else binding.progressBar.visibility = View.GONE
        }

        binding.searchList.apply {
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

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
}