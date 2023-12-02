package com.example.countries_diparshan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countries_diparshan.R
import com.example.countries_diparshan.models.Country

class CountryAdapter(
    private val countryList:MutableList<Country>,
    private val onFavoriteClickHandler: (Int) -> Unit
):

    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {

        init {
            itemView.findViewById<Button>(R.id.btn_favourite).setOnClickListener {
                onFavoriteClickHandler(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.country_row_layout, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        // 1. Get the current country
        val currCountry:Country = countryList.get(position)

        // 2. Populate the UI with the country details
        val tvCountryName = holder.itemView.findViewById<TextView>(R.id.tv_country_name)
        tvCountryName.text = currCountry.name.common
    }
    }
