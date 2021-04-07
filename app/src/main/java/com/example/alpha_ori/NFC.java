package com.example.alpha_ori;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.charset.Charset;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                }
                // Process the messages array.

            }
        }
    }

    private Intent intent;
    Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
    NdefRecord uriRecord = new NdefRecord(
            NdefRecord.TNF_ABSOLUTE_URI ,
            "https://developer.android.com/index.html".getBytes(Charset.forName("US-ASCII")),
            new byte[0], new byte[0]);
    NdefRecord mimeRecord = NdefRecord.createMime("application/vnd.com.example.android.beam",
            "Beam me up, Android".getBytes(Charset.forName("US-ASCII")));

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