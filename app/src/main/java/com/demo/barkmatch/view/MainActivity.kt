package com.demo.barkmatch.view


import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.barkmatch.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {

            // Reload the last fragment in the backstack
            val lastFragmentTag = savedInstanceState.getString("breedSearchResultsFragment")
            if (lastFragmentTag != null) {
                supportFragmentManager.findFragmentByTag(lastFragmentTag)?.let { fragment ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, lastFragmentTag)
                        .addToBackStack(null)
                        .commit()
                }
            }
        } else {
            val breedSearchFragment = BreedSearchFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, breedSearchFragment, "breedSearchFragment")
                .commit()
        }



    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        val backstackState = Bundle()

        val backstackCount = supportFragmentManager.backStackEntryCount
        for (i in 0 until backstackCount) {
            val backstackEntry = supportFragmentManager.fragments[i]
            val fragmentState = supportFragmentManager.saveFragmentInstanceState(backstackEntry)
            backstackState.putParcelable(backstackEntry.tag, fragmentState)
        }

        outState.putBundle("backstack", backstackState)
    }
}