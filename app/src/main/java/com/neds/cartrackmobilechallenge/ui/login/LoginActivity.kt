package com.neds.cartrackmobilechallenge.ui.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neds.cartrackmobilechallenge.R
import com.neds.cartrackmobilechallenge.data.viewModels.LoginViewModel
import com.neds.cartrackmobilechallenge.databinding.ActivityLoginBinding
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import com.neds.cartrackmobilechallenge.ui.BaseActivity
import com.neds.cartrackmobilechallenge.ui.countryPicker.CountryPickerDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val vm: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        initViews()
        observeData()

    }

    private fun initViews() {
        binding.lifecycleOwner = this
        binding.vm = vm

    }

    private fun observeData() {

        vm.countryClickEvent.observe(this, {
            Lg.d("observeData: countryClickEvent")
            CountryPickerDialog.newInstance(vm.country.value,
                object : CountryPickerDialog.CountryPickerListener {
                    override fun onCountrySelected(country: String) =
                        vm.country.postValue(country)
                }).show(supportFragmentManager, CountryPickerDialog.TAG)
        })
    }
}