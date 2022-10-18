package com.marat.retrofittest.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.marat.retrofittest.R
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment(), RikAdapter.CharacterListListener {

    private lateinit var binding: FragmentCharacterListBinding
    private var adapter: RikAdapter? = null
    private val viewModel: ListFragmentViewModel by viewModels()

    companion object {
        const val ITEM_ARGUMENT = "argument"
        fun newInstance() = CharacterListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        adapter = RikAdapter(this)
        binding.rcView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characterList.observe(viewLifecycleOwner) { list ->
            binding.progressBar.visibility = View.VISIBLE
            list?.let { adapter?.setData(it.results) }
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onCharacterClick(item: Result, img: View) {
        img.findNavController().navigate(
            R.id.action_characterListFragment_to_detailInformationFragment,
            bundleOf(
                ITEM_ARGUMENT to item,
            ),
            null,
            FragmentNavigatorExtras(img to item.id.toString())
        )
    }
}