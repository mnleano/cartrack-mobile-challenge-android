package com.neds.cartrackmobilechallenge.ui.countryPicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neds.cartrackmobilechallenge.databinding.RowCountryPickerBinding

class CountryPickerAdapter(
    private val countries: MutableList<String>,
    private val selectedCountry: String?,
    private val listener: CountryPickerListener
) : RecyclerView.Adapter<CountryPickerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            RowCountryPickerBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = countries[position]
        holder.binding.country = country
        holder.binding.isSelected = country == selectedCountry
        holder.binding.root.setOnClickListener { listener.onCountryClicked(country) }
    }

    override fun getItemCount(): Int =
        countries.size


    inner class MyViewHolder(val binding: RowCountryPickerBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface CountryPickerListener {
        fun onCountryClicked(country: String)
    }

}