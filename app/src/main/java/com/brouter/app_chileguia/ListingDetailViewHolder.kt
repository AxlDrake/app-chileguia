package com.brouter.app_chileguia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.brouter.app_chileguia.databinding.ItemDetailBinding
import com.squareup.picasso.Picasso

class ListingDetailViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private  val binding = ItemDetailBinding.bind(view)

    fun bind(listing: Listing)
    {
        binding.txtTitle.text = listing.title

        if(!listing.cover.isNullOrEmpty()){
            Picasso.get().load("https://www.chileguia.cl/" + listing.cover).into(binding.imgLogo)
        }
        else{
            Picasso.get().load("https://www.chileguia.cl/assets/images/no-logo.png" ).into(binding.imgLogo)
        }

    }
}