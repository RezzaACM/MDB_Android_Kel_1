package com.demo.data.karyawan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailOfficeActivity extends AppCompatActivity {

    TextView nameOffice;
    TextView addressOffice;
    TextView phoneOffice;
    TextView emailOffice;
    TextView descOffice;
    ImageView imgOffice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_office);

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

    }
}