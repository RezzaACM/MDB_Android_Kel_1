package com.demo.data.karyawan.menu;

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

public class OfficeActivity extends AppCompatActivity {

    private RestProcess rest_class;
    ListView listOfficee;
    ListAdapter adapter;
    ArrayList<HashMap<String, String>> dataOffice = new ArrayList <HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        getSupportActionBar().hide();

        rest_class = new RestProcess();
        listOfficee = (ListView) findViewById(R.id.listOffice);
        getDataOffice(null);
    }

    private void getDataOffice(final View view){
        HashMap<String, String> apiData = new HashMap<>();
        apiData = rest_class.apiSetting();
        AsyncHttpClient client = new AsyncHttpClient();
        String base_url;

        base_url = apiData.get("str_ws_addr") + "/api/training/office/format/json";

        client.setBasicAuth(apiData.get("str_ws_user"),apiData.get("str_ws_pass"));
        client.post(base_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode,cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
            String resp_content = "";
            try{
                resp_content = new String(responseBody, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }try{
                displayDataOffice(view,resp_content);

            }catch (Throwable t) {
                    Toast.makeText(OfficeActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(OfficeActivity.this, "Konkesi gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayDataOffice (View view, String resp_content){
        try{
            dataOffice = rest_class.getJsonData(resp_content);
            if(dataOffice.get(0).get("var_result").equals("1")){

                dataOffice.remove(0);
                adapter = new ListAdapter(OfficeActivity.this, dataOffice, 2);
                listOfficee.setAdapter(adapter);
                Toast.makeText(OfficeActivity.this, "Koneksi Berhasil", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(OfficeActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException e){
            Toast.makeText(OfficeActivity.this, "Koneksi gagal", Toast.LENGTH_SHORT).show();
        }


    }
}
