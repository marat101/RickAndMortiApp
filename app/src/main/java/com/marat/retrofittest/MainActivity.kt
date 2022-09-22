package com.marat.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marat.retrofittest.listfragment.CharacterListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container_view, SplashFragment.newInstance()).commit()
    }
}