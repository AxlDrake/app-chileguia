package com.brouter.app_chileguia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListingDetailAdapter(private  val listing: ArrayList<Listing>):RecyclerView.Adapter<ListingDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListingDetailViewHolder(layoutInflater.inflate(R.layout.item_detail, parent, false))
    }

    override fun onBindViewHolder(holder: ListingDetailViewHolder, position: Int) {
        val item : Listing = listing[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = 1
}