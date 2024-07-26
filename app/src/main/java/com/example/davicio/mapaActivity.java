package com.example.davicio;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class mapaActivity extends sinBarraSuperior implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText ingresodireccion;
    Button buscar;
    GoogleMap map;
    Geocoder geocoder;
    String direccion, titulo;
    private ExecutorService executorService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        ingresodireccion = findViewById(R.id.ingresodireccion);
        buscar = findViewById(R.id.btnmapsearch);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        geocoder = new Geocoder(this);

        // Obtener los datos del Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            titulo = bundle.getString("nombre");
            direccion = bundle.getString("direccion");

            if (direccion != null && !direccion.isEmpty()) {
                ingresodireccion.setText(direccion);
                buscarDireccion(direccion);
            }
        }

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccion = ingresodireccion.getText().toString();

                Uri map = Uri.parse("geo:0,0?q="+ Uri.encode(direccion));
                Intent intent=new Intent(Intent.ACTION_VIEW,map);
                startActivity(intent);
            }
        });
    }

    private void buscarDireccion(String direccion) {
        try {
            List<Address> addresses = geocoder.getFromLocationName(direccion, 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                double latitud = address.getLatitude();
                double longitud = address.getLongitude();
                LatLng ubicacion = new LatLng(latitud, longitud);
                mostrarUbicacion(ubicacion, titulo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarUbicacion(LatLng ubicacion, String titulo) {
        if (map != null) {
            map.clear();
            map.addMarker(new MarkerOptions().position(ubicacion).title(titulo));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15.0f));
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
        map.setOnMapLongClickListener(this);
        if (direccion != null && !direccion.isEmpty()) {
            buscarDireccion(direccion);
        }
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
    }




}
