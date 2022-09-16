package com.marat.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainContainer = findViewById(R.id.fragment_container_view)

        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container_view, CharacterListFragment.newInstance()
        ).commit()



        // 1+1=3

    }
}