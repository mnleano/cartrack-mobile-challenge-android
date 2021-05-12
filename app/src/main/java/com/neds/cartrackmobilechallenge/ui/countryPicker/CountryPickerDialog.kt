package com.neds.cartrackmobilechallenge.ui.countryPicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.neds.cartrackmobilechallenge.R
import com.neds.cartrackmobilechallenge.databinding.DialogCountryPickerBinding
import com.neds.cartrackmobilechallenge.infrastructure.Lg

class CountryPickerDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogCountryPickerBinding

    private var countries = mutableListOf<String>()
    private var filteredList = mutableListOf<String>()

    var selectedCountry: String? = null
    var listener: CountryPickerListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCountryPickerBinding.inflate(inflater)

        initCountryPicker()
        queryList()
        return binding.root
    }

    private fun initCountryPicker() {

        countries.addAll(resources.getStringArray(R.array.countries))
        countries.map { Lg.d("onCreateView: country=$it") }

        binding.rvCountries.adapter = CountryPickerAdapter(filteredList, selectedCountry,
            object : CountryPickerAdapter.CountryPickerListener {
                override fun onCountryClicked(country: String) {
                    listener?.onCountrySelected(country)
                    dismiss()
                }
            })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                queryList()
                return true
            }
        })

        dialog?.let {
            (it as BottomSheetDialog?)?.let { bottomSheetDialog ->
                Lg.d("onCreateView->bottomSheet: state=${bottomSheetDialog.behavior.state}")
                bottomSheetDialog.setOnShowListener {
                    val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                    val layoutParams = bottomSheet?.layoutParams
                    layoutParams?.height = WindowManager.LayoutParams.MATCH_PARENT
                    bottomSheet?.layoutParams = layoutParams
                }
            }
        }
    }

    private fun queryList() {
        filteredList.clear()
        filteredList.addAll(countries.filter { c -> c.contains(binding.searchView.query, true) })
        binding.rvCountries.adapter?.notifyDataSetChanged()

        Lg.d("queryList: filtered.size=${filteredList.size}")
    }

    interface CountryPickerListener {
        fun onCountrySelected(country: String)
    }

    companion object {
        const val TAG = "CountryPickerDialog"

        fun newInstance(
            selectedCountry: String?,
            listener: CountryPickerListener
        ): CountryPickerDialog =
            CountryPickerDialog().apply {
                this.selectedCountry = selectedCountry
                this.listener = listener
            }
    }
}