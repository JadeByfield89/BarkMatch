package com.demo.barkmatch.view

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.barkmatch.R
import com.demo.barkmatch.model.MatchResult
import com.demo.barkmatch.view.adapter.BreedSearchResultsAdapter

class BreedSearchResultsFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breed_search_results, container, false)

        // Allow the user to go back and search again
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up and initialize the recyclerview and set a grid layout manager to it that has 2 columns
        recyclerView = view.findViewById(R.id.breed_search_results_rv) as RecyclerView


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels

        recyclerView = view.findViewById(R.id.breed_search_results_rv) as RecyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 2)

        var adapter = BreedSearchResultsAdapter(screenWidth)
        recyclerView?.adapter = adapter

        // Set up the adapter using the data we received from the bundle, if it's available
        arguments?.getParcelable<MatchResult>(ARG_MATCH_RESULT)?.let {
            adapter.updateData(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        private const val ARG_MATCH_RESULT = "match_result"

        fun newInstance(matchResult: MatchResult): BreedSearchResultsFragment {
            val args = Bundle().apply {
                putParcelable(ARG_MATCH_RESULT, matchResult)
            }
            return BreedSearchResultsFragment().apply {
                arguments = args
            }
        }

        fun newInstance(imageIds: ArrayList<String>): BreedSearchResultsFragment {

            val args = Bundle().apply {
                putStringArrayList(ARG_MATCH_RESULT, imageIds)
            }
            return BreedSearchResultsFragment().apply {
                arguments = args
            }
        }
    }
}
