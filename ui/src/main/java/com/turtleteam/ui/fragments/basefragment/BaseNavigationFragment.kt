package com.turtleteam.ui.fragments.basefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.turtleteam.ui.R
import com.turtleteam.ui.databinding.FragmentBaseBinding
import com.turtleteam.ui.fragments.favoritesfragment.FavoritesFragment
import com.turtleteam.ui.fragments.listfragment.CharacterListFragment

class BaseNavigationFragment : Fragment() {

    private lateinit var binding: FragmentBaseBinding
    private val homefragm by lazy { CharacterListFragment() }
    private val favfragm by lazy { FavoritesFragment() }

    @SuppressLint("DetachAndAttachSameFragment")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(inflater, container, false)

        if (parentFragmentManager.fragments.size == 1) {
            parentFragmentManager.beginTransaction().apply {
                add(R.id.base_fragment_container, favfragm, "2")
                add(R.id.base_fragment_container, homefragm, "1")
            }.commit()
        }

        binding.baseBottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_btn -> {
                    parentFragmentManager.beginTransaction()
                        .hide(parentFragmentManager.findFragmentByTag("2")!!)
                        .show(parentFragmentManager.findFragmentByTag("1")!!).commit()
                    true
                }
                else -> {
                    parentFragmentManager.beginTransaction()
                        .hide(parentFragmentManager.findFragmentByTag("1")!!)
                        .show(parentFragmentManager.findFragmentByTag("2")!!)
                        .commit()
                    true
                }
            }
        }
        return binding.root
    }
}