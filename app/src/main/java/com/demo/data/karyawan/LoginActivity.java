package com.demo.data.karyawan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    String var_string_username, var_string_password;

    private RestProcess rest_class;

    ArrayList<HashMap<String, String>> arrayLogin = new ArrayList<HashMap<String, String>>();

    TextView tvRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hide action bar
        getSupportActionBar().hide();

        rest_class = new RestProcess();

        //Mengdeklarasikan komponen – komponen variable
        final EditText edtUsername = (EditText) findViewById(R.id.edtUsername);
        final EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegist = findViewById(R.id.tvRegis);


        //Saat tombol login di klik maka halaman akan berpindah ke Main Activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                var_string_username = edtUsername.getText().toString();
                var_string_password = edtPassword.getText().toString();

                if (var_string_username.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Username Harus diisi !", Toast.LENGTH_LONG).show();
                } else if (var_string_password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Password Harus diisi !", Toast.LENGTH_SHORT).show();
                } else {
                    //Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    //LoginActivity.this.startActivity(mainIntent);
                    loginProcess(v);
                }
            }
        });

        tvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regisIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(regisIntent);
            }
        });
    }

    private void loginProcess(final View view){

        HashMap<String, String> apiData = new HashMap<String,String>();
        apiData = rest_class.apiSetting();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String base_url;

        base_url = apiData.get("str_ws_addr") + "/api/training/auth/format/json";
        params.put("var_cell_phone", var_string_username);
        params.put("var_password", var_string_password);


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
                    displayLogin(view,resp_content);

                } catch (Throwable t) {
                    Toast.makeText(LoginActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(LoginActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();
            }
        });
       }



    private void displayLogin(View view, String resp_content) {
        try {
            arrayLogin = rest_class.getJsonData(resp_content);

            if (arrayLogin.get(0).get("var_result").equals("1")) {

                Intent main_intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(main_intent);
                finish();

            } else if(arrayLogin.get(0).get("var_result").equals("0")) {
                Toast.makeText(LoginActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();
            }


        } catch (JSONException e) {
            Toast.makeText(LoginActivity.this, "Koneksi Gagal !", Toast.LENGTH_LONG).show();

        }
    }

}
