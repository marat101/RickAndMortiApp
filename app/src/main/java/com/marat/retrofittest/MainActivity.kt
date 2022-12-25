package com.marat.retrofittest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marat.retrofittest.ui.fragments.listfragment.CharacterListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.fragments.isEmpty())(
        supportFragmentManager.beginTransaction().add(R.id.fragment_container_view, CharacterListFragment()).commit())
    }
}