package com.marat.retrofittest.detaillistfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.marat.retrofittest.R

class DetailListFragment : Fragment(R.layout.fragment_detail_information) {

    companion object {
        fun newInstance() = DetailListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}