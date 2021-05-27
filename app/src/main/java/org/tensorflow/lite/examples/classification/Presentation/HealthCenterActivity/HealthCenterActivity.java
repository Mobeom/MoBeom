package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.SelectiveClinicJson;
import org.tensorflow.lite.examples.classification.R;

import java.util.List;


public class HealthCenterActivity extends AppCompatActivity implements OnMapReadyCallback {

    private double longitude;
    private double latitude;
    private LocationManager lm;
    public GoogleMap gMap;
    public List<SelectiveClinicJson> clinics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_center);

        clinics = (List<SelectiveClinicJson>) getIntent().getSerializableExtra("clinics");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        //addMarker();
    }

    protected void addMarker() {
        for (SelectiveClinicJson clinic : clinics) {
            LatLng lo = new LatLng(clinic.x, clinic.y);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(clinic.name);
            markerOptions.snippet(clinic.phone);
            markerOptions.position(lo);

            gMap.addMarker(markerOptions);
        }
    }

    protected void GetCurrentLocation(){
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null){
            Log.e("TAG", "GPS is On");
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        else {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, gpsLocationListener);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, gpsLocationListener);
        }
    }

    final LocationListener gpsLocationListener = new LocationListener(){
        @Override
        public void onLocationChanged(@NonNull Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(@NonNull String provider) {
        }
        @Override
        public void onProviderDisabled(@NonNull String provider) {
        }
    };


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        GetCurrentLocation();
        addMarker();
        LatLng location = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16)); // 가까이 보고 싶으면 숫자를 올린다
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16)); // 가까이 보고 싶으면 숫자를 올린다
    }
}

