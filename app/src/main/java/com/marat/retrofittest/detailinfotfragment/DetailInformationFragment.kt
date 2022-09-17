package com.marat.retrofittest.detailinfotfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.marat.retrofittest.R

class DetailInformationFragment : Fragment(R.layout.fragment_detail_information) {

    companion object {
        fun newInstance() = DetailInformationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}