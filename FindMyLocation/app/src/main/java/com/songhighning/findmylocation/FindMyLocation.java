package com.songhighning.findmylocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class FindMyLocation extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "AlexsMessage";
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private int MY_LOCATION_PERMISSION_REQUEST = 0;
    private double longitude = -34, latitude = 151;
    private LocationManager mLocationManager;
    private Button mFindMyLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Intent gpsOptionsIntent = new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_LOCATION_PERMISSION_REQUEST);

        }



        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //String bestProvider = getLastKnownLocationProvider();
        final String bestProvider = LocationManager.GPS_PROVIDER;
        mLocationManager.requestLocationUpdates(bestProvider, 0, 10F, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                LatLng currentLocation = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker in currentLocation"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });


        mFindMyLastLocation = (Button)findViewById(R.id.FindLastLocationbutton);

        mFindMyLastLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getParent(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_LOCATION_PERMISSION_REQUEST);

                }
                Location lastKnownLocation =  mLocationManager.getLastKnownLocation(bestProvider);
                if(lastKnownLocation != null){
                    longitude = lastKnownLocation.getLongitude();
                    latitude = lastKnownLocation.getLatitude();
                }

                LatLng currentLocation = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker in currentLocation"));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, mMap.getMaxZoomLevel()*0.70F));

            }
        });


        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng currentLocation = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker in currentLocation"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, mMap.getMaxZoomLevel()*0.35F));
    }

    private String getLastKnownLocationProvider() {
        //mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        String bestProvider = null;
        for (String provider : providers) {
            bestProvider = provider;
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_LOCATION_PERMISSION_REQUEST);

            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                Log.i(TAG, " provider is:   " + provider + ". Location is null");
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                Log.i(TAG, " Found best Location:" + provider);
                bestLocation = l;


            }
        }
        return bestProvider;
    }
}
