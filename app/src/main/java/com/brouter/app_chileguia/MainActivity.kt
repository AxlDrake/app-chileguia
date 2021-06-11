package com.brouter.app_chileguia



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.brouter.app_chileguia.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding:ActivityMainBinding

    private lateinit var adapter: ListingAdapter
    private  lateinit var detailAdapter : ListingDetailAdapter

    private var listingList = ArrayList<Listing>()
    
    private var detailListing = ArrayList<Listing>()

    private var progressBar : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allListings()

        binding.svListings.setOnQueryTextListener(this)
    }

    private fun initListingsView(listings : ListingsResponse) {

        if(listings.status == "200"){
            listingList = (listings.result) as ArrayList<Listing>
        }

        adapter = ListingAdapter(listingList)
        binding.rvListings.setHasFixedSize(true)
        binding.rvListings.layoutManager = LinearLayoutManager(this)

        binding.rvListings.adapter = adapter

        adapter.setOnItemClickListener(object : ListingAdapter.onItemClickListener{
            override fun onItemClick(position: Int, id: Int) {
                Toast.makeText(this@MainActivity, "Clickeaste aca $id" , Toast.LENGTH_SHORT).show()
                getListing(id)
            }

        })

        progressBar = binding.pbLoading

        if(progressBar!!.isEnabled){
            progressBar!!.visibility = View.INVISIBLE
        }

    }

    private fun initDetailListingView(listings: ListingsResponse)
    {
        if (listings.status=="200")
        {
            detailListing = (listings.result) as ArrayList<Listing>
        }

        detailAdapter = ListingDetailAdapter(detailListing)
        binding.rvListings.setHasFixedSize(true)
        binding.rvListings.layoutManager = LinearLayoutManager(this)

        binding.rvListings.adapter = detailAdapter

    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.chileguia.cl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){

        CoroutineScope(Dispatchers.IO).launch {

            val requestBody : RequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", query)
                .build()

            val call: Response<ListingsResponse> = getRetrofit().create(APIService::class.java).getSearchListings(requestBody)
            val listings:ListingsResponse? = call.body()
            runOnUiThread {
                /*if (call.isSuccessful) {

                    val listed : List<Listing> = (listings?.result ?: emptyArray<Listing>()) as List<Listing>
                    listingList.clear()
                    listingList.addAll(listed)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                } */

                if (listings?.status == "200") {
                    initListingsView(listings)
                } else {
                    showError()
                }

                hideKeyboard()

            }

        }
    }

    private fun allListings(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ListingsResponse> = getRetrofit().create(APIService::class.java).getAllListings("listings/get")
            val listings = call.body()
            runOnUiThread {
                if (listings?.status == "200") {
                    initListingsView(listings)
                    /*val listed : List<Listing> = (listings?.result ?: emptyArray<Listing>()) as List<Listing>
                    listingList.clear()
                    listingList.addAll(listed)
                    adapter.notifyDataSetChanged() */
                } else {
                    showError()
                }
            }

        }
    }

    private fun getListing(id:Int)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ListingsResponse> = getRetrofit().create(APIService::class.java).getDetailListing("listings/get/$id")
            val listing = call.body()

            runOnUiThread {
                if(listing?.status == "200")
                {
                    initDetailListingView(listing)
                }
                else
                {
                    showError()
                }
            }
        }

    }

    private fun showError(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT ).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){

            progressBar = binding.pbLoading
            progressBar!!.visibility = View.VISIBLE

            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }


}


