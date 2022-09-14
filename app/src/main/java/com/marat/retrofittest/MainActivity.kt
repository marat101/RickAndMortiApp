package com.marat.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.marat.retrofittest.data.api.MainViewModel
import com.marat.retrofittest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private lateinit var mainContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainContainer = findViewById(R.id.fragment_container_view)

        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container_view, CharacterListFragment.newInstance()
        ).commit()

        // 1+1=3
        vm = ViewModelProvider(this)[MainViewModel::class.java]

    }
}