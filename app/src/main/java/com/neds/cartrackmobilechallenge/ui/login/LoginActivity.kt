package com.neds.cartrackmobilechallenge.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import com.neds.cartrackmobilechallenge.R
import com.neds.cartrackmobilechallenge.data.enums.Status
import com.neds.cartrackmobilechallenge.data.viewModels.LoginViewModel
import com.neds.cartrackmobilechallenge.databinding.ActivityLoginBinding
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import com.neds.cartrackmobilechallenge.ui.BaseActivity
import com.neds.cartrackmobilechallenge.ui.MainActivity
import com.neds.cartrackmobilechallenge.ui.countryPicker.CountryPickerDialog
import com.neds.cartrackmobilechallenge.ui.util.SnackbarBuilder
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
        vm.addValidator(Validator(binding))
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

        vm.loggedInResult.observe(this, {
            Lg.d("observeData: loggedInResult=${it.status}")
            if (it.status == Status.SUCCESS) startClearTaskActivity(
                Intent(
                    this@LoginActivity,
                    MainActivity::class.java
                )
            )
            if (it.status == Status.ERROR) it.message?.let { message ->
                SnackbarBuilder.show(
                    binding.mainContainer,
                    message,
                    getString(R.string.retry),
                    {
                        vm.clearFields()
                    }
                )
            }
        })

        vm.errorMessage.observe(this, {
            Lg.d("observeData: errorMessage=$it")
        })
    }
}