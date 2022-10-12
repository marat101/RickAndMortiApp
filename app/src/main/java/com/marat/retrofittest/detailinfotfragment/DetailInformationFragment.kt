package com.marat.retrofittest.detailinfotfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.databinding.FragmentDetailInformationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailInformationFragment : Fragment() {

    companion object {
        const val ITEM_ARGUMENT = "argument"

        fun newInstance(item: Result): DetailInformationFragment {
            val bundle = Bundle().apply {
                putParcelable(ITEM_ARGUMENT, item)
            }
            return DetailInformationFragment().apply {
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentDetailInformationBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInformationBinding.inflate(inflater, container, false)

        val result = arguments?.getParcelable<Result>(ITEM_ARGUMENT)
        binding.image.load(result?.image)
        binding.characterName.text = result?.name
        binding.characterStatus.text = "Status: ${result?.status}"
        binding.characterSpecies.text = "Scecies ${result?.species}"
        binding.characterType.text = "Type: ${result?.type}"
        binding.characterGender.text = "Gender ${result?.gender}"
        binding.characterCreationDate.text = "Created ${result?.created}"

        return binding.root
    }
}