package com.demo.data.karyawan.menu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.data.karyawan.ListAdapter;
import com.demo.data.karyawan.R;
import com.demo.data.karyawan.RestProcess;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class EmployeeActivity extends AppCompatActivity {

    //API process
    private RestProcess rest_class;

    //ListView variable
    ListView lvDataKaryawan;

    //Declare Class ListAdapter
    ListAdapter adapter;

    //Declare ArrayList
    ArrayList<HashMap<String, String>> datakaryawan_arrayList = new ArrayList <HashMap<String, String>>();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        context = EmployeeActivity.this;
        rest_class = new RestProcess();
        lvDataKaryawan = (ListView) findViewById(R.id.lvDataKaryawan);

        getDataKaryawan(null);
    }

    private void getDataKaryawan(final View view){

        HashMap<String, String> apiData = new HashMap<String,String>();
        apiData = rest_class.apiSetting();
        AsyncHttpClient client = new AsyncHttpClient();
        String base_url;

        base_url = apiData.get("str_ws_addr") + "/api/training/employee/format/json";

        client.setBasicAuth(apiData.get("str_ws_user"),apiData.get("str_ws_pass"));
        client.post(base_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String resp_content ="";
                try {
                    resp_content = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    displayDataKaryawan(view,resp_content);

                } catch (Throwable t) {
                    Toast.makeText(EmployeeActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(EmployeeActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void displayDataKaryawan(View view, String resp_content) {
        try {
            datakaryawan_arrayList = rest_class.getJsonData(resp_content);

            if(datakaryawan_arrayList.get(0).get("var_result").equals("1")) {

                datakaryawan_arrayList.remove(0);
                adapter = new ListAdapter(EmployeeActivity.this, datakaryawan_arrayList, 1, context);
                lvDataKaryawan.setAdapter(adapter);
                Toast.makeText(EmployeeActivity.this, "Koneksi Behasil !", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(EmployeeActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            Toast.makeText(EmployeeActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();

        }

    }
}
