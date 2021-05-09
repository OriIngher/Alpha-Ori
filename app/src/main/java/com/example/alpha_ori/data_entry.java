package com.example.alpha_ori;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class data_entry<adp> extends AppCompatActivity {
    Trainee trainee;
    Spinner spin;
    String programName[] = {"Push-Pull-Legs", "Full Body", "A-B"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        spin = (Spinner) findViewById(R.id.spin);
        spin.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, programName);
        spin.setAdapter(adp);

    }

    public class SpinDemo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}