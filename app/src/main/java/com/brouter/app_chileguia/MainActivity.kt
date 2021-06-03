package com.brouter.app_chileguia


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.brouter.app_chileguia.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: ListingAdapter
    private val listingList = ArrayList<Listing>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.svListings.setOnQueryTextListener(this)
        allListings()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ListingAdapter(listingList)
        binding.rvListings.layoutManager = LinearLayoutManager(this)
        binding.rvListings.adapter = adapter

    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.chileguia.cl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ListingsResponse> = getRetrofit().create(APIService::class.java).getAllListings("$query/get")
            val listings:ListingsResponse? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {

                    val listi : List<Listing> = (listings?.result ?: emptyArray<Listing>()) as List<Listing>
                    listingList.clear()
                    listingList.addAll(listi)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }

        }
    }

    private fun allListings(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ListingsResponse> = getRetrofit().create(APIService::class.java).getAllListings("listings/get")
            val listings:ListingsResponse? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {

                    val listi : List<Listing> = (listings?.result ?: emptyArray<Listing>()) as List<Listing>
                    listingList.clear()
                    listingList.addAll(listi)
                    adapter.notifyDataSetChanged()
                } else {
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
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}


