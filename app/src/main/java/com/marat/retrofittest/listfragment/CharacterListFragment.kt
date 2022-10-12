package com.marat.retrofittest.listfragment

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marat.retrofittest.R
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.databinding.FragmentCharacterListBinding
import com.marat.retrofittest.detailinfotfragment.DetailInformationFragment

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private var adapter = RikAdapter(onClick = { clickOnItem(it) })

    companion object {
        fun newInstance() = CharacterListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)
        binding.rcView.adapter = adapter

        viewModel.characterList.observe(viewLifecycleOwner) { list ->
            binding.progressBar.visibility = View.VISIBLE
            list?.let { adapter.setData(it.results) }
            Log.e("aaa", viewModel.characterList.toString())
            binding.progressBar.visibility = View.INVISIBLE
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun clickOnItem(item: Result) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, DetailInformationFragment.newInstance(item))
            addToBackStack(CharacterListFragment::class.java.name)
        }.commit()
    }
}