package com.example.alpha_ori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NFC extends AppCompatActivity {

     Button scan ;
     TextView infoscan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_f_c);
        scan = findViewById(R.id.scan);
        infoscan = findViewById(R.id.infoscan);
    }

    public void scan(View view) {
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * switch activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();

        if ((st.equals("storage"))) {
            Intent si = new Intent(this, Storage.class);
            startActivity(si);
        }
        if ((st.equals("authentication"))) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if ((st.equals("NFC"))) {
            Intent si = new Intent(this, NFC.class);
            startActivity(si);
        }



        return true;
    }
}