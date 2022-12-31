package com.marat.retrofittest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class SplashFragment : Fragment(R.layout.fragment_splash) {
    companion object {
        fun newInstance() = SplashFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}