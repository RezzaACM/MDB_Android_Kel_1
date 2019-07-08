package com.demo.data.karyawan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class DetailEmployeeActivity extends AppCompatActivity {
    TextView nip;
    TextView nama;
    TextView jenis_kelamin;
    TextView alamat;
    TextView gol_darah;
    TextView agama;
    TextView status;
    TextView kewarganegaraan;
    TextView berlaku_hingga;
    TextView tempat_buat;
    TextView tanggal_buat;
    ImageView imgemployee;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_employee);

        ctx = DetailEmployeeActivity.this;

//        Deklarasi variabel dengan object pada layout
        nip = findViewById(R.id.tvNomorIndukPegawai);
        nama = findViewById(R.id.tvEmployeeName);
        jenis_kelamin = findViewById(R.id.tvGenderEmployee);
        alamat = findViewById(R.id.tvAddressEmployee);
        gol_darah = findViewById(R.id.tvGolonganDarah);
        agama = findViewById(R.id.tvAgama);
        status = findViewById(R.id.tvStatusPerkawinan);
        kewarganegaraan = findViewById(R.id.tvKewarganegaraan);
        berlaku_hingga = findViewById(R.id.tvBerlakuHingga);
        tempat_buat = findViewById(R.id.tvTempatBuat);
        tanggal_buat = findViewById(R.id.tvTanggalBuat);
        imgemployee = findViewById(R.id.imgEmployee);

//      Deklarasi variabel dengan object pada database
        final String nip1 = getIntent().getStringExtra("nomor_induk_pegawai");
        final String nama1 = getIntent().getStringExtra("employee_name");
        final String jenis_kelamin1 = getIntent().getStringExtra("gender");
        final String alamat1 = getIntent().getStringExtra("address");
        final String gol_darah1 = getIntent().getStringExtra("gol_darah");
        final String agama1 = getIntent().getStringExtra("agama");
        final String status1 = getIntent().getStringExtra("status_perkawinan");
        final String kewarganegaraan1 = getIntent().getStringExtra("kewarganegaraan");
        final String berlaku_hingga1 = getIntent().getStringExtra("berlaku_hingga");
        final String tempat_buat1 = getIntent().getStringExtra("tempat_buat");
        final String tanggal_buat1 = getIntent().getStringExtra("tanggal_buat");
        final String image1 = getIntent().getStringExtra("base_url");

        nip.setText
                ("NIK : "+nip1);
        nama.setText
                ("Nama              : "+nama1);
        jenis_kelamin.setText
                ("Jenis Kelamin     : "+jenis_kelamin1);
        alamat.setText
                ("Alamat            : "+alamat1);
        gol_darah.setText
                ("Golongan Darah    : "+gol_darah1);
        agama.setText
                ("Agama             : "+agama1);
        status.setText
                ("Status Perkawinan : "+status1);
        kewarganegaraan.setText
                ("Kewarganegaraan   : "+kewarganegaraan1);
        berlaku_hingga.setText
                ("Berlaku Hingga    : "+berlaku_hingga1);
        tempat_buat.setText(tempat_buat1);
        alamat.setText(alamat1);
        tanggal_buat.setText(tanggal_buat1);
        Picasso.get().load((image1)).into(imgemployee);

//        Dialog settingsDialog = new Dialog(this);
//        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout
//                , null));
//        settingsDialog.show();

        final ImageView image = new ImageView(this);
        Picasso.get().load((image1)).into(image);

        imgemployee.setOnClickListener(new View.OnClickListener() {
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
                                DetailEmployeeActivity.this.finish();
                            }
                        });


                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();

            }
        });


    }












    }
