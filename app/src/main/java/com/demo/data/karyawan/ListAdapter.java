package com.demo.data.karyawan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListAdapter extends BaseAdapter {
    Dialog myDialog;
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private int fragment_position=0;
    private static LayoutInflater inflater=null;
    private String PACKAGE_NAME;
    Context context;
    ImageView image;


    public ListAdapter(Activity a, ArrayList<HashMap<String, String>> d, int fragment_pos, Context context) {

        data=d;
        activity = a;
        fragment_position = fragment_pos;
        inflater = (LayoutInflater)activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        PACKAGE_NAME = activity.getPackageName();
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;

        switch (fragment_position) {

            case 1:
                if (convertView == null)
                    vi = inflater.inflate(R.layout.lv_employee, null);
                CardView cv= vi.findViewById(R.id.cv);
                ImageView btn=vi.findViewById(R.id.imgbtn_view);
                TextView tvEmployeeName = (TextView) vi.findViewById(R.id.tvEmployeeName);
                TextView tvEmployeeNumber = (TextView) vi.findViewById(R.id.tvEmployeeNumber);
                TextView tvEmployeeAddress = (TextView) vi.findViewById(R.id.tvEmployeeAddress);
                TextView tvEmployeeGender = (TextView) vi.findViewById(R.id.tvEmployeeGender);
                ImageView image = vi.findViewById(R.id.img_employee);

                HashMap<String, String> empList = new HashMap<String, String>();

                empList = data.get(position);

                tvEmployeeName.setText(empList.get("employee_name"));
                tvEmployeeNumber.setText(empList.get("nomor_induk_pegawai"));
                tvEmployeeAddress.setText(empList.get("address"));
                tvEmployeeGender.setText(empList.get("gender"));
                Picasso.get().load(empList.get("base_url")).into(image);

                final HashMap<String, String> finalEmpList1 = empList;

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(context, DetailEmployeeActivity.class);
                        intent.putExtra("nomor_induk_pegawai", finalEmpList1.get("nomor_induk_pegawai"));
                        intent.putExtra("employee_name", finalEmpList1.get("employee_name"));
                        intent.putExtra("address", finalEmpList1.get("address"));
                        intent.putExtra("gol_darah", finalEmpList1.get("gol_darah"));
                        intent.putExtra("tempat_buat", finalEmpList1.get("tempat_buat"));
                        intent.putExtra("agama", finalEmpList1.get("agama"));
                        intent.putExtra("status_perkawinan", finalEmpList1.get("status_perkawinan"));
                        intent.putExtra("kewarganegaraan", finalEmpList1.get("kewarganegaraan"));
                        intent.putExtra("berlaku_hingga", finalEmpList1.get("berlaku_hingga"));
                        intent.putExtra("gender", finalEmpList1.get("gender"));
                        intent.putExtra("tempat_buat", finalEmpList1.get("tempat_buat"));
                        intent.putExtra("tanggal_buat", finalEmpList1.get("tanggal_buat"));
                        intent.putExtra("base_url", finalEmpList1.get("base_url"));

                        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    }
                });

                final ImageView image1 = new ImageView(context);
                Picasso.get().load(empList.get("base_url")).into(image1);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        // set title dialog
                        alertDialogBuilder.setTitle("Photo Employee");

                        // set pesan dari dialog
                        alertDialogBuilder
                                .setIcon(R.mipmap.ic_launcher)
                                .setCancelable(false)
                                .setView(image1)
                                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // jika tombol diklik, maka akan menutup activity ini
                                    }
                                });


                        // membuat alert dialog dari builder
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // menampilkan alert dialog
                        alertDialog.show();

                    }
                });
                break;

            case 2:
                if (convertView == null)
                    vi = inflater.inflate(R.layout.lv_office, null);
                ImageView btn1=vi.findViewById(R.id.imgbtn);

                TextView tvOfficeName = (TextView) vi.findViewById(R.id.officeName);
                TextView tvOfficeAddress = (TextView) vi.findViewById(R.id.addressOffice);
                TextView tvOfficePhone = (TextView) vi.findViewById(R.id.phoneOffice);
//                TextView tvEmployeeGender = (TextView) vi.findViewById(R.id.tvEmployeeGender);
                ImageView officeImage = vi.findViewById(R.id.img_office);

                HashMap<String, String> officeList = new HashMap<String, String>();

                officeList = data.get(position);

                tvOfficeName.setText(officeList.get("office_name"));
                tvOfficeAddress.setText(officeList.get("office_address"));
                tvOfficePhone.setText(officeList.get("cell_phone"));
//                tvEmployeeGender.setText(empList.get("gender"));
                Picasso.get().load(officeList.get("base_url")).into(officeImage);

                final HashMap<String, String> finalEmpList2 = officeList;
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(context, DetailOfficeActivity.class);
                        intent.putExtra("office_name", finalEmpList2.get("office_name"));
                        intent.putExtra("office_address", finalEmpList2.get("office_address"));
                        intent.putExtra("cell_phone", finalEmpList2.get("cell_phone"));
                        intent.putExtra("email", finalEmpList2.get("email"));
                        intent.putExtra("office_description", finalEmpList2.get("office_description"));
                        intent.putExtra("base_url", finalEmpList2.get("base_url"));
                        intent.putExtra("location_gps", finalEmpList2.get("location_gps"));


                        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                });

                final ImageView image2 = new ImageView(context);
                Picasso.get().load(finalEmpList2.get("base_url")).into(image2);

                officeImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        // set title dialog
                        alertDialogBuilder.setTitle("Photo Office");

                        // set pesan dari dialog
                        alertDialogBuilder
                                .setIcon(R.mipmap.ic_launcher)
                                .setCancelable(false)
                                .setView(image2)
                                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // jika tombol diklik, maka akan menutup activity ini
                                    }
                                });


                        // membuat alert dialog dari builder
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // menampilkan alert dialog
                        alertDialog.show();

                    }
                });

                break;

            default:

                break;
        }
        return vi;
    }
}