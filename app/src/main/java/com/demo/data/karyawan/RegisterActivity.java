package com.demo.data.karyawan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {

    String var_fullName, var_cellPhone, var_email, var_password, var_type;

    private RestProcess rest_class;
    EditText var_fullname1,var_email1,var_cellPhone1,var_password1;

    ArrayList<HashMap<String, String>> arrayRegis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        rest_class = new RestProcess();

        var_fullname1 = findViewById(R.id.edtUsername);
        var_cellPhone1 = findViewById(R.id.edtCellPhone);
        var_email1 = findViewById(R.id.edtEmail);
       var_password1 = findViewById(R.id.edtPassword);
        Button btnRegis = findViewById(R.id.btnRegis);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var_fullName = var_fullname1.getText().toString();
                var_cellPhone = var_cellPhone1.getText().toString();
                var_email = var_email1.getText().toString();
                var_password = var_password1.getText().toString();
                var_type = "save";

                if (var_fullName.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Username Harus diisi !", Toast.LENGTH_LONG).show();
                } else if (var_cellPhone.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Nomor HP Harus diisi !", Toast.LENGTH_SHORT).show();
                }else if(var_email.length()==0){
                    Toast.makeText(RegisterActivity.this, "Email Harus diisi !", Toast.LENGTH_SHORT).show();
                }else if(var_password.length() == 0){
                    Toast.makeText(RegisterActivity.this, "Password", Toast.LENGTH_SHORT).show();
                }else{
                    regisProcess(view);
                }



            }
        });

    }

    private void regisProcess(final View view){
        HashMap<String, String> apiData = new HashMap<>();
        apiData = rest_class.apiSetting();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String base_url;

        base_url = apiData.get("str_ws_addr") + "/api/training/register/format/json";
        params.put("var_cell_phone", var_cellPhone);
        params.put("var_password", var_password);
        params.put("var_type", var_type);
        params.put("var_full_name", var_fullName);
        params.put("var_email", var_email);

        client.setBasicAuth(apiData.get("str_ws_user"),apiData.get("str_ws_pass"));
        client.post(base_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String resp_content ="";
                try {
                    resp_content = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    displayRegister(view,resp_content);

                } catch (Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Koneksi Gaga !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(RegisterActivity.this, "Koneksi Gagal2 !", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void displayRegister(View view, String resp_content) {
        try {
            arrayRegis = rest_class.getJsonData(resp_content);

            if (arrayRegis.get(0).get("var_result").equals("1")) {

                Intent main_intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(main_intent);
                finish();

            } else if(arrayRegis.get(0).get("var_result").equals("0")) {
                Toast.makeText(RegisterActivity.this, "Koneksi Gagal3 !", Toast.LENGTH_LONG).show();
            }


        } catch (JSONException e) {
            Toast.makeText(RegisterActivity.this, "Koneksi Gagal4 !", Toast.LENGTH_LONG).show();

        }
    }

}

