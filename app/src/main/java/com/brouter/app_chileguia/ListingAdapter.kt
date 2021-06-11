package com.brouter.app_chileguia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.sql.RowId

class ListingAdapter(private val list: ArrayList<Listing>):RecyclerView.Adapter<ListingViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener
    {
        fun onItemClick(position: Int, id: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener)
    {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListingViewHolder(layoutInflater.inflate(R.layout.item_listing, parent, false), mListener)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val item: Listing =list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

}