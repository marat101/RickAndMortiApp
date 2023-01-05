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
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import coil.load
import com.marat.retrofittest.R
import com.marat.retrofittest.databinding.FragmentDetailInformationBinding
import com.marat.retrofittest.ui.base.BaseFragment
import com.marat.retrofittest.ui.fragments.listfragment.CharacterListFragment
import com.turtleteam.domain.model.Result
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailInformationFragment : BaseFragment<FragmentDetailInformationBinding>() {
    private val viewModel by viewModel<DetailInformationViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(CharacterListFragment.ITEM_ARGUMENT, Result::class.java)
        } else {
            arguments?.getParcelable(CharacterListFragment.ITEM_ARGUMENT)
        }
        if (args?.type == "") binding.characterType.isVisible = false
        if (viewModel.isFavoriteCheck(args?.id!!)) binding.favoriteView?.setImageResource(R.drawable.dislike)

        binding.favoriteView?.setOnClickListener {
            val isFavorite = viewModel.clickOnLike(args.id)
            if (isFavorite) {
                binding.favoriteView?.setImageResource(R.drawable.dislike)
            } else {
                binding.favoriteView?.setImageResource(R.drawable.like)
            }
            setFragmentResult("refresh", bundleOf())
        }

        binding.apply {
            val gend = args.gender

            characterImg.transitionName = args.id.toString()
            characterImg.load(args.image)
            characterName.text = args.name
            characterStatus.text = "Статус: ${args.status}"
            characterSpecies.text = "Вид: ${args.species}"
            characterType.text = "Тип: ${args.type}"
            characterGender.text = "Пол: $gend"
            val dateformatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ROOT)
            val parsed = dateformatter.parse(args.created)
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