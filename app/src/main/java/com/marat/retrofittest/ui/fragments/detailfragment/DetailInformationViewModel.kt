package com.marat.retrofittest.ui.fragments.detailfragment

import androidx.lifecycle.ViewModel
import com.turtleteam.domain.usecase.local.CheckIdUseCase
import com.turtleteam.domain.usecase.local.SaveIdsUseCase

class DetailInformationViewModel(private val isFavorite: CheckIdUseCase, private val saveId: SaveIdsUseCase): ViewModel() {

    fun isFavoriteCheck(id: Int) = isFavorite.execute(id)

    fun clickOnLike(id: Int) = saveId.execute(id)
}