package com.marat.retrofittest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.marat.retrofittest.listfragment.CharacterListFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {
    companion object {
        fun newInstance() = SplashFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(800)
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container_view, CharacterListFragment.newInstance()).commit()
        }
    }
}