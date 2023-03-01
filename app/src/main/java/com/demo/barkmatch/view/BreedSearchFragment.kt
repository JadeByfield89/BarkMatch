package com.demo.barkmatch.view

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.demo.barkmatch.R
import com.demo.barkmatch.viewmodel.BreedSearchViewModel


class BreedSearchFragment : Fragment() {

    private lateinit var viewModel: BreedSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_breed_search, container, false)
        var searchField = view.findViewById(R.id.searchEditText) as EditText
        var searchButton = view.findViewById(R.id.searchButton) as Button


        searchButton.setOnClickListener {

            val breedName = searchField.text.toString()

            viewModel.searchDogsByBreed(breedName)

            viewModel.matchResults.observe(viewLifecycleOwner){ matchResult ->

                // Make sure we have something to show before loading a new fragment
                if (matchResult != null && matchResult.messageList.isNotEmpty()) {
                    val breedSearchResultsFragment = BreedSearchResultsFragment.newInstance(matchResult)

                    // Show the results fragment
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, breedSearchResultsFragment)
                        .addToBackStack(null)
                        .commit()
                }
                else
                    showNoResultsDialog(context)
            }

            // Show a message to the user in case of an error
            viewModel.errorMessage.observe(viewLifecycleOwner){message ->
                if(message.isNotEmpty()){
                    Toast.makeText(context, message, Toast.LENGTH_SHORT)
                }
            }
        }

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[BreedSearchViewModel::class.java]


        return view
    }

   private fun showNoResultsDialog(context: Context?){
       val alertDialog = AlertDialog.Builder(context)
           .setTitle("No Dog Pics Found")
           .setMessage("Oh no! Looks like we couldn't find any cute dog pics based on your query. Would you like to see some pics of my dog Zion instead? (I promise he's adorable)")
           .setPositiveButton("Yes") { dialog, which ->


               dialog.dismiss()

               val localImageList = ArrayList<String>()
               val packageName = context?.packageName

               for (i in 1..12) {
                   val resName = "zion$i"
                   val resId = resources.getIdentifier(resName, "drawable", packageName)
                   if (resId != 0) {
                       val uri = Uri.parse("android.resource://$packageName/$resId")
                       localImageList.add(uri.toString())
                   } else {
                       Log.e("BreedSearchFragment", "Invalid resource ID for $resName")
                   }
               }

               val breedSearchResultsFragment = BreedSearchResultsFragment.newInstance(localImageList)

               // Show the results fragment
               parentFragmentManager.beginTransaction()
                   .replace(R.id.fragment_container, breedSearchResultsFragment)
                   .addToBackStack(null)
                   .commit()


           }
           .setNegativeButton("No") { dialog, which ->
               dialog.dismiss()
           }
           .create()

       alertDialog.show()
   }


}