package com.example.foodeli_v1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient

class DisplayLocationActivity : AppCompatActivity() {

    private lateinit var selectedLocationTextView: TextView
    private lateinit var placesClient: PlacesClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_location)

        selectedLocationTextView = findViewById(R.id.selectedLocationTextView)

        // Initialize Places API
        Places.initialize(applicationContext, getString(R.string.google_maps_api_key))
        placesClient = Places.createClient(this)

        // Retrieve the LatLng from the intent
        val selectedLocation: LatLng? = intent.getParcelableExtra("selectedLocation")

        // Display the selected location on the map
        if (selectedLocation != null) {
            displayLocation(selectedLocation)
        }
    }

    private fun displayLocation(location: LatLng) {
        // Convert LatLng to human-readable address
        val request = FetchPlaceRequest.builder(location.toString(), listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS))
            .build()

        placesClient.fetchPlace(request).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val response: FetchPlaceResponse = task.result!!

                val place = response.place
                val address = place.address ?: "Address not available"

                // Display the address in the UI
                selectedLocationTextView.text = address
            } else {
                // Handle error
                selectedLocationTextView.text = "Error fetching address"
            }
        }
    }
}
