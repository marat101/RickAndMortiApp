package com.marat.retrofittest.ui.fragments.detailfragment

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import coil.load
import com.marat.retrofittest.data.model.Result
import com.marat.retrofittest.databinding.FragmentDetailInformationBinding
import com.marat.retrofittest.ui.base.BaseFragment
import com.marat.retrofittest.ui.fragments.listfragment.CharacterListFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailInformationFragment : BaseFragment<FragmentDetailInformationBinding>() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TransitionInflater.from(context).inflateTransition(android.R.transition.move).apply {
            sharedElementEnterTransition = this
            duration = 150
        }

        val args = arguments?.getParcelable<Result>(CharacterListFragment.ITEM_ARGUMENT)
        if (args?.type == "") binding.characterType.isVisible = false

        Log.e("date", args?.created.toString())


        binding.apply {
            characterImg.transitionName = args?.id.toString()
            characterImg.load(args?.image.toString())
            characterName.text = args?.name
            characterStatus.text = "Status: ${args?.status}"
            characterSpecies.text = "Scecies: ${args?.species}"
            characterType.text = "Type: ${args?.type}"
            characterGender.text = "Gender: ${args?.gender}"
            val dateformatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ROOT)
            val parsed = dateformatter.parse(args?.created)
            val formatted = SimpleDateFormat("dd MMMM yyyy года", Locale.forLanguageTag("ru")).format(parsed)
            characterCreationDate.text = "Created: $formatted"
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDetailInformationBinding =
        FragmentDetailInformationBinding.inflate(inflater, container, false)
}