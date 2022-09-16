package com.marat.retrofittest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.marat.retrofittest.adapter.RikAdapter
import com.marat.retrofittest.databinding.FragmentCharacterListBinding
import com.marat.retrofittest.detaillistfragment.DetailListFragment

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private lateinit var mainContainer: FrameLayout
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

        binding.rcView.adapter = adapter
        adapter.addTestList(testlist())

        return binding.root
    }
    private fun clickOnItem(item: Character) {
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container_view, DetailListFragment.newInstance()).commit()
    }

    private fun testlist(): List<Character> {
        return listOf<Character>(
            Character(1, "Ренат", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
            Character(1, "Клон Рената", Constants.IMG_URL),
        )
    }
}