package com.marat.retrofittest.ui.fragments.detailfragment

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import coil.load
import com.marat.retrofittest.databinding.FragmentDetailInformationBinding
import com.marat.retrofittest.ui.base.BaseFragment
import com.marat.retrofittest.ui.fragments.listfragment.CharacterListFragment
import com.turtleteam.domain.model.Result
import java.util.*

class DetailInformationFragment : BaseFragment<FragmentDetailInformationBinding>() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TransitionInflater.from(context).inflateTransition(android.R.transition.move).apply {
            sharedElementEnterTransition = this
            duration = 150
        }

        val args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(CharacterListFragment.ITEM_ARGUMENT, Result::class.java)
        } else {
            arguments?.getParcelable<Result>(CharacterListFragment.ITEM_ARGUMENT)
        }
        if (args?.type == "") binding.characterType.isVisible = false

        binding.apply {
            val gend = args?.gender

            characterImg.transitionName = args?.id.toString()
            characterImg.load(args?.image.toString())
            characterName.text = args?.name
            characterStatus.text = "Статус: ${args?.status}"
            characterSpecies.text = "Вид: ${args?.species}"
            characterType.text = "Тип: ${args?.type}"
            characterGender.text = "Пол: $gend"
            val dateformatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ROOT)
            val parsed = dateformatter.parse(args?.created)
            val formatted =
                SimpleDateFormat("dd MMMM yyyy года", Locale.forLanguageTag("ru")).format(parsed)
            var created = "Создан"
            if (gend == "Female") created = "Создана"
            characterCreationDate.text = "$created $formatted"
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDetailInformationBinding =
        FragmentDetailInformationBinding.inflate(inflater, container, false)
}