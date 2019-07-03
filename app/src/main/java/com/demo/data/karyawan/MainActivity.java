package com.demo.data.karyawan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        } else if (item.getItemId() == R.id.tentang_apps) {
            startActivity(new Intent(this, AboutActivity.class));
        }else if(item.getItemId() == R.id.office){
            startActivity(new Intent(this, OfficeActivity.class));
        }else if (item.getItemId() == R.id.keluar) {
            startActivity(new Intent(this, LoginActivity.class));
        }
       return true;
    }
}



