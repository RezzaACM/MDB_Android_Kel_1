package com.demo.data.karyawan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.squareup.picasso.Picasso;
import com.here.android.mpa.mapping.SupportMapFragment;

import java.io.IOException;

public class HereMapsOffice extends AppCompatActivity {


    TextView nameOffice;
    TextView addressOffice;
    TextView phoneOffice;
    TextView emailOffice;
    TextView descOffice;
    ImageView imgOffice;
    Context ctx;
    Double lat = 0.0;
    Double lng = 0.0;
    String officename;
    private Map map = null;
    private SupportMapFragment mapFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_here_maps_office);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);


//      Deklarasi variabel dengan object pada layout
        nameOffice = findViewById(R.id.tvNameOFF);
        addressOffice = findViewById(R.id.tvAddressOFF);
        phoneOffice = findViewById(R.id.tvPhoneOFF);
        emailOffice = findViewById(R.id.tvEmailOFF);
        descOffice = findViewById(R.id.tvDesckripsiOFF);

        imgOffice = findViewById(R.id.imgOffice);

//      Deklarasi variabel dengan object pada database
        officename = getIntent().getStringExtra("office_name");
        final String alamatOffice1 = getIntent().getStringExtra("office_address");
        final String phoneOffice1 = getIntent().getStringExtra("cell_phone");
        final String emailOffice1 = getIntent().getStringExtra("email");
        final String descOffice1 = getIntent().getStringExtra("office_description");

        final String image1 = getIntent().getStringExtra("base_url");
        final String maps_latlng = getIntent().getStringExtra("location_gps");
        Log.d("debug", "onCreate: " + maps_latlng);
        String[] separated = maps_latlng.split(",");
        lat = Double.parseDouble(String.valueOf(separated[0]));
        lng = Double.parseDouble(String.valueOf(separated[1]));

        nameOffice.setText
                (" " + officename);
        addressOffice.setText
                ("◆ " + alamatOffice1);
        phoneOffice.setText
                ("◆ " + phoneOffice1);
        emailOffice.setText
                ("◆ " + emailOffice1);
        descOffice.setText
                (" " + descOffice1);


        Picasso.get().load((image1)).into(imgOffice);

        final ImageView image = new ImageView(this);
        Picasso.get().load((image1)).into(image);

        imgOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);

                // set title dialog
                alertDialogBuilder.setTitle("Photo Employee");

                // set pesan dari dialog
                alertDialogBuilder
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setView(image)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // jika tombol diklik, maka akan menutup activity ini
                                HereMapsOffice.this.finish();
                            }
                        });


                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();

            }
        });

        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
// retrieve a reference of the map from the map fragment
                    map = mapFragment.getMap();
                    // Create a custom marker image
                    com.here.android.mpa.common.Image myImage =
                            new com.here.android.mpa.common.Image();
                    try {
                        myImage.setImageResource(R.drawable.ic_map);
                    } catch (IOException e) {
                        finish();
                    }
// Create the MapMarker
                    MapMarker myMapMarker =
                            new MapMarker(new GeoCoordinate(lat, lng), myImage);
                    map.addMapObject(myMapMarker);
// Set the map center to the Vancouver region (no animation)
                    map.setCenter(new GeoCoordinate(lat, lng, 0.0),
                            Map.Animation.LINEAR);
// Set the zoom level to the average between min and max
                    map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                } else {
                    System.out.println("ERROR: Cannot initialize Map Fragment");
                }
            }
        });


    }
}
