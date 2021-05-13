package com.neds.cartrackmobilechallenge.ui.userDetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.neds.cartrackmobilechallenge.R
import com.neds.cartrackmobilechallenge.data.views.UserView
import com.neds.cartrackmobilechallenge.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityUserDetailsBinding
    private lateinit var user: UserView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.let {
            user = it.getSerializable(KEY_USER) as UserView
            binding.data = user
        } ?: finish()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val KEY_USER = "User"
        fun makeIntent(context: Context, user: UserView): Intent =
            Intent(context, UserDetailsActivity::class.java)
                .putExtra(KEY_USER, user)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Add a marker in Sydney and move the camera
        val location = LatLng(user.address.geo.lat, user.address.geo.lng)
        googleMap.addMarker(MarkerOptions().position(location).title(user.name))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
    }
}