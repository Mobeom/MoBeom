package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.RecyclerViewAdapther;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.SelectiveClinicJson;
import org.tensorflow.lite.examples.classification.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HealthCenterActivity extends AppCompatActivity implements OnMapReadyCallback {     //@copyright for 이동우

    private double longitude;
    private double latitude;
    private LocationManager lm;
    public GoogleMap gMap;
    public List<SelectiveClinicJson> clinics;

    private RecyclerViewAdapther recyclerViewAdapther;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {      //@copyright for 이동우
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_center);

        //인근 보건소 작업
        clinics = (List<SelectiveClinicJson>) getIntent().getSerializableExtra("clinics");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    protected void addMarker() {        //@copyright for 이동우
        for (SelectiveClinicJson clinic : clinics) {
            LatLng lo = new LatLng(clinic.x, clinic.y);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(clinic.name);
            markerOptions.snippet(clinic.phone);
            markerOptions.position(lo);

            gMap.addMarker(markerOptions);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            gMap.setMyLocationEnabled(true);
        }
    }

    protected void GetCurrentLocation() {       //@copyright for 이동우
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
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

    final LocationListener gpsLocationListener = new LocationListener(){        //@copyright for 이동우
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

    class SortbyDistance implements Comparator<SelectiveClinicJson>         //@copyright for 이동우
    {
        @Override
        public int compare(SelectiveClinicJson t1, SelectiveClinicJson t2) {
            double compare1 = Math.pow(t1.getX()-latitude, 2) + Math.pow(t1.getY()-longitude, 2);
            double compare2 = Math.pow(t2.getX()-latitude, 2) + Math.pow(t2.getY()-longitude, 2);
            return (int)((compare1-compare2)*100);
        }
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {      //@copyright for 이동우
        gMap = googleMap;
        GetCurrentLocation();
        addMarker();
        LatLng location = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13)); // 가까이 보고 싶으면 숫자를 올린다


        Collections.sort(clinics, new SortbyDistance());

        List<SelectiveClinicJson> priority = clinics.subList(0,4);

        recyclerViewAdapther = new RecyclerViewAdapther(priority);
        recyclerView.setAdapter(recyclerViewAdapther);

    }
}

