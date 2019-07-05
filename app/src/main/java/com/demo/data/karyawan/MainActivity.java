package com.demo.data.karyawan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

import com.demo.data.karyawan.menu.AboutActivity;
import com.demo.data.karyawan.menu.EmployeeActivity;
import com.demo.data.karyawan.menu.OfficeActivity;

public class MainActivity extends AppCompatActivity {

    //membuat variabel view
    private WebView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (WebView) this.findViewById(R.id.webview);


        view.getSettings().setJavaScriptEnabled(true); //untuk mengaktifkan javascript
        view.setWebViewClient(new MyBrowser());
        view.loadUrl("http://www.multiintisarana.com");
        view.setWebChromeClient(new WebChromeClient()); //ketika webview selesai load page loading akan dismis
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menambahkan item item menu kedalam class
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Mengarahkan halaman yang akan dituju berdasarkan id yang sudah ditentukan di optionmenu
        if (item.getItemId()==R.id.data_karyawan){
            startActivity(new Intent(this, EmployeeActivity.class));
        }else if(item.getItemId() == R.id.office){
            startActivity(new Intent(this, OfficeActivity.class));
        } else if (item.getItemId() == R.id.tentang_apps) {
            startActivity(new Intent(this, AboutActivity.class));
        }else if (item.getItemId() == R.id.keluar) {
            startActivity(new Intent(this, LoginActivity.class));
        }
       return true;
    }
}



