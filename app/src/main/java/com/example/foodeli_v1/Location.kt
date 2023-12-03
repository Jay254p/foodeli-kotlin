package com.example.foodeli_v1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode

class Location : AppCompatActivity(), OnMapReadyCallback {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var placesClient: PlacesClient
    private lateinit var editedLocation: LatLng

    private lateinit var selectedPlaceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        selectedPlaceTextView = findViewById(R.id.selectedPlaceTextView)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Initialize Places API
        Places.initialize(applicationContext, getString(R.string.google_maps_api_key))
        placesClient = Places.createClient(this)

        // Check and request location permissions
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission already granted
            // Initialize the map and location search
            initializeMap()
            initializeLocationSearch()
        } else {
            // Request location permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
        val confirmButton: Button = findViewById(R.id.confirmButton)
        confirmButton.setOnClickListener {
            // Create an Intent to redirect to MainActivity
            val intent = Intent(this, MainActivity::class.java)

            // Start MainActivity
            startActivity(intent)
        }

    }

    private fun initializeMap() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Map is ready
        // Enable the user's location button
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        googleMap.isMyLocationEnabled = true

        // Get the user's last known location and move the camera
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val currentLocation = LatLng(location.latitude, location.longitude)
                    moveCameraToLocation(currentLocation)
                    addMarker(currentLocation, "Current Location")
                }
            }

        // Set up map click listener for editing the location
        googleMap.setOnMapClickListener { point ->
            editedLocation = point
            googleMap.clear() // Clear existing markers
            addMarker(editedLocation, "Edited Location")
        }
    }

    private fun moveCameraToLocation(location: LatLng) {
        // Move the camera to the specified location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

    private fun addMarker(location: LatLng, title: String) {
        // Add a marker to the map
        googleMap.addMarker(MarkerOptions().position(location).title(title))
    }

    private fun initializeLocationSearch() {
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        // Set up the AutocompleteActivityMode to fullscreen for full-screen auto-complete.
        autocompleteFragment.setActivityMode(AutocompleteActivityMode.FULLSCREEN)

        // Set up a PlaceSelectionListener to handle the selection of a place from the search results.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // Clear existing markers
                googleMap.clear()
                // Handle the selected place, e.g., move the camera to the selected location.
                val location = place.latLng
                moveCameraToLocation(location)
                addMarker(location, place.name ?: "")
                // Update the UI with the selected place's name
                selectedPlaceTextView.text = place.name ?: ""
                // Update the edited location for the "Confirm Location" button click
                editedLocation = location
            }

            override fun onError(status: Status) {
                // Handle errors
                Log.e("Places API", "An error occurred: $status")
            }
        })
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, initialize the map
                initializeMap()
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
            }
        }
    }
}
