package com.demo.data.karyawan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private int fragment_position=0;
    private static LayoutInflater inflater=null;
    private String PACKAGE_NAME;

    public ListAdapter(Activity a,ArrayList<HashMap<String, String>> d, int fragment_pos) {
        data=d;
        activity = a;
        fragment_position = fragment_pos;
        inflater = (LayoutInflater)activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        PACKAGE_NAME = activity.getPackageName();
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


                break;

            case 2:
                if (convertView == null)
                    vi = inflater.inflate(R.layout.lv_office, null);

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

                break;

            default:

                break;
        }
        return vi;
    }
}
