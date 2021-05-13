package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.SelectiveClinicJson;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityCheckListBinding;


public class HealthCenterActivity extends AppCompatActivity {

    private double longitude;
    private double latitude;
    private LocationManager lm;
    private HealthCenterController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_center);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.googleMap);

        //retrofit
        controller = new HealthCenterController();
        controller.mapFragment = mapFragment;
        controller.start();

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //구글 지도 불러오기
    }
    protected void openMap(MapFragment mapFragment){

        mapFragment.getMapAsync(googleMap -> {
            GetCurrentLocation();
            LatLng location = new LatLng(latitude, longitude);

            Log.e("clinic size", controller.resource.size()+" ");
            for (SelectiveClinicJson clinic : controller.resource) {
                LatLng lo = new LatLng(clinic.x, clinic.y);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(clinic.name);
                markerOptions.snippet(clinic.address + clinic.phone);     //세부 설명
                markerOptions.position(lo);
                Log.e("TAG", "in for loop" + clinic.name);

                googleMap.addMarker(markerOptions);
            }

            //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16)); // 가까이 보고 싶으면 숫자를 올린다
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16)); // 가까이 보고 싶으면 숫자를 올린다

        });
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

}

