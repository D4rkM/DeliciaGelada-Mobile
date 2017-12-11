package br.com.deliciagelada.deliciagelada.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.deliciagelada.deliciagelada.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Long latitude, longitude;
    private TextView nomeLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        nomeLocal = (TextView) findViewById(R.id.nomeLocal);

        Intent intent = getIntent();
        latitude = intent.getLongExtra("latitude", 0);
        longitude = intent.getLongExtra("longitude", 0);
        nomeLocal.setText(intent.getStringExtra("nomeLocal"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng delicia = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(delicia).title("Estamos aqui!!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(delicia));
    }


}
