package com.demo.barkmatch.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.barkmatch.R
import com.demo.barkmatch.view.BreedSearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     val breedSearchFragment = BreedSearchFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, breedSearchFragment)
        .commit()

    }
}