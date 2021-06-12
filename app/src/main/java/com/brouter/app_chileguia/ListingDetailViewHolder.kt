package com.brouter.app_chileguia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.brouter.app_chileguia.databinding.ItemDetailBinding
import com.squareup.picasso.Picasso

class ListingDetailViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemDetailBinding.bind(view)

    fun bind(listing: Listing)
    {
        binding.tvTitle.text = listing.title
        binding.tvId.text = listing.id.toString()
        binding.tvDescription.text = listing.description
        binding.tvContactPerson.text = listing.contactPerson
        binding.tvEmail.text = listing.email
        binding.tvPhoneNumber.text = listing.phoneNumber
        binding.tvMobilePhone.text = listing.mobileNumber

        if(!listing.cover.isNullOrEmpty()){
            Picasso.get().load("https://www.chileguia.cl/" + listing.cover).into(binding.imgLogo)
        }
        else{
            Picasso.get().load("https://www.chileguia.cl/assets/images/no-logo.png" ).into(binding.imgLogo)
        }

    }
}