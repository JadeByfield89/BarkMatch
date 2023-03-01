package com.demo.barkmatch.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.demo.barkmatch.R
import com.demo.barkmatch.model.MatchResult
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BreedSearchResultsAdapter(): RecyclerView.Adapter<BreedSearchResultsAdapter.ViewHolder>() {

    private var matchResult: MatchResult = MatchResult(emptyList())

    fun updateData(matchResult: MatchResult){
        this.matchResult = matchResult
        //notifyDataSetChanged()
    }

    fun updateDataLocal(imageIds: ArrayList<String>){
        this.matchResult = MatchResult(imageIds)
        //notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val breedSearchResultIv: ImageView = itemView.findViewById(R.id.breedImage)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_breed_search_result, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imageUrl = matchResult.messageList[position]

        // Load the image using Picasso
        Picasso.get().load(imageUrl).resize(200,200).centerCrop().into(holder.breedSearchResultIv, object: Callback {
            override fun onSuccess() {
                // Hide the progress bar if it's visible
                holder.progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                // Hide the progress bar if it's visible
                holder.progressBar.visibility = View.GONE

                // Show an error message if there was an error loading the image
                Toast.makeText(holder.itemView.context, "Error loading image", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getItemCount(): Int {
        return matchResult.messageList.size
    }
}
