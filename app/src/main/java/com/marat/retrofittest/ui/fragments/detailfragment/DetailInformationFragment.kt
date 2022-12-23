package com.marat.retrofittest.ui.fragments.detailfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import coil.load
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.databinding.FragmentDetailInformationBinding
import com.marat.retrofittest.ui.base.BaseFragment
import com.marat.retrofittest.ui.fragments.listfragment.CharacterListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailInformationFragment : BaseFragment<FragmentDetailInformationBinding>() {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TransitionInflater.from(context).inflateTransition(android.R.transition.move).apply {
            sharedElementEnterTransition = this
            duration = 150
        }

        val args = arguments?.getParcelable<Result>(CharacterListFragment.ITEM_ARGUMENT)
        if (args?.type == "") binding.characterType.isVisible = false

        binding.apply {
            characterImg.transitionName = args?.id.toString()
            characterImg.load(args?.image.toString())
            characterName.text = args?.name
            characterStatus.text = "Status: ${args?.status}"
            characterSpecies.text = "Scecies ${args?.species}"
            characterType.text = "Type: ${args?.type}"
            characterGender.text = "Gender ${args?.gender}"
            characterCreationDate.text = "Created ${args?.created}"
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDetailInformationBinding =
        FragmentDetailInformationBinding.inflate(inflater, container, false)
}