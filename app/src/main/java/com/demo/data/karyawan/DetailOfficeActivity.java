package com.demo.data.karyawan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class DetailOfficeActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView nameOffice;
    TextView addressOffice;
    TextView phoneOffice;
    TextView emailOffice;
    TextView descOffice;
    ImageView imgOffice;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_office);
        ctx = DetailOfficeActivity.this;

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//      Deklarasi variabel dengan object pada layout
        nameOffice = findViewById(R.id.tvNameOFF);
        addressOffice = findViewById(R.id.tvAddressOFF);
        phoneOffice = findViewById(R.id.tvPhoneOFF);
        emailOffice = findViewById(R.id.tvEmailOFF);
        descOffice = findViewById(R.id.tvDesckripsiOFF);

        imgOffice = findViewById(R.id.imgOffice);

//      Deklarasi variabel dengan object pada database
        final String nameOffice1 = getIntent().getStringExtra("office_name");
        final String alamatOffice1 = getIntent().getStringExtra("office_address");
        final String phoneOffice1 = getIntent().getStringExtra("cell_phone");
        final String emailOffice1 = getIntent().getStringExtra("email");
        final String descOffice1 = getIntent().getStringExtra("office_description");
        
        final String image1 = getIntent().getStringExtra("base_url");
        final String maps_latlng = getIntent().getStringExtra("location_gps");


      
        nameOffice.setText
                (" "+nameOffice1);
        addressOffice.setText
                ("◆ "+alamatOffice1);
        phoneOffice.setText
                ("◆ "+phoneOffice1);
        emailOffice.setText
                ("◆ "+emailOffice1);
        descOffice.setText
                (" "+descOffice1);


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
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // jika tombol diklik, maka akan menutup activity ini
                                DetailOfficeActivity.this.finish();
                            }
                        });


                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-6.239199, 106.832840)).title("Marker"));

    }
}