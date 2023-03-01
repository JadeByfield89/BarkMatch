package com.demo.barkmatch.viewmodel

import androidx.lifecycle.ViewModel

/**
 * This is the ViewModel that would back the search results fragment. Since we are simply displaying
 * the result images for now, there isn't any logic that needs to be added here.
 *
 * If we wanted to add functionality to this view by, let's say allowing the user to select a particular photo
 * and then show more info about that breed, we could have functions in here to fetch additional data and pass those results back to the fragment.
 */
class BreedSearchResultsViewModel: ViewModel() {
}