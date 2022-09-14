package com.marat.retrofittest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marat.retrofittest.adapter.RikAdapter
import com.marat.retrofittest.databinding.FragmentCharacterListBinding

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private lateinit var binding: FragmentCharacterListBinding
    private var adapter = RikAdapter()

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