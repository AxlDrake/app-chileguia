package com.brouter.app_chileguia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.brouter.app_chileguia.databinding.ItemListingBinding
import com.squareup.picasso.Picasso


class ListingViewHolder(view : View, listener: ListingAdapter.onItemClickListener):RecyclerView.ViewHolder(view) {

    private val binding = ItemListingBinding.bind(view)

    fun bind(list: Listing){
        binding.tvId.text = list.id.toString()
        binding.tvTitle.text =  list.title
        binding.tvDescription.text = list.description

        if(!list.cover.isNullOrEmpty()){
            Picasso.get().load("https://www.chileguia.cl/" + list.cover).into(binding.txtImage)
        }
        else{
            Picasso.get().load("https://www.chileguia.cl/assets/images/no-logo.png" ).into(binding.txtImage)
        }
    }

    init {

        itemView.setOnClickListener {

            listener.onItemClick(adapterPosition, binding.tvId.text.toString().toInt())

        }

    }

}