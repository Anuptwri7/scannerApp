package com.soori.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView barcodeTextView;

    private BroadcastReceiver barcodeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals("com.soori.myapplication.SCAN_ACTION")) {
                String barcodeData = intent.getStringExtra("com.symbol.datawedge.data_string");
                if (barcodeData != null) {
                    Log.d("Barcode", "Scanned barcode: " + barcodeData);
                    barcodeTextView.setText(barcodeData);
                } else {
                    Log.d("Barcode", "No barcode data received");
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextView
        barcodeTextView = findViewById(R.id.barcodeTextView);

        // Register the broadcast receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.soori.myapplication.SCAN_ACTION");
        registerReceiver(barcodeReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the broadcast receiver
        unregisterReceiver(barcodeReceiver);
    }
}
