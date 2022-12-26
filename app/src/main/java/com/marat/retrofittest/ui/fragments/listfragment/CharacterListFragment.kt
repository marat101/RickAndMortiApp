package com.marat.retrofittest.ui.fragments.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import com.marat.retrofittest.R
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.databinding.FragmentCharacterListBinding
import com.marat.retrofittest.ui.base.BaseFragment
import com.marat.retrofittest.ui.fragments.detailfragment.DetailInformationFragment
import com.marat.retrofittest.ui.fragments.listfragment.adapter.RikAdapter
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

        binding.rcView.apply {
            adapter = charactersAdapter
            postponeEnterTransition()
            this.doOnPreDraw { startPostponedEnterTransition() }
        }
        binding.progressBar.visibility = View.GONE

        lifecycleScope.launch {
            viewModel.characterList.collect {
                charactersAdapter.submitData(it)
            }
        }
    }

    override fun onCharacterClick(item: Result, img: View) {
        parentFragmentManager.beginTransaction().addToBackStack("listfragment")
            .addSharedElement(img, item.id.toString()).replace(R.id.fragment_container_view,
                DetailInformationFragment::class.java,
                bundleOf(ITEM_ARGUMENT to item)).commit()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentCharacterListBinding =
        FragmentCharacterListBinding.inflate(inflater, container, false)
}