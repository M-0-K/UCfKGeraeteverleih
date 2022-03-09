package com.example.ucgeraeteverleih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/*
 * Prüfung Gescannter Geräte
 * Übergabe von Objekten zwischen Activities
 * DB login über sharred Prefferences
 *
 * */

public class MainActivity extends AppCompatActivity {

    private Button btVerbinden;
    private TextView tvStatus;
    private EditText ptUSR;
    private EditText ptIpadress;
    private EditText pPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btVerbinden = (Button) findViewById(R.id.btVerbinden);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        ptUSR = (EditText) findViewById(R.id.ptUSR);
        ptIpadress = (EditText) findViewById(R.id.ptIpadress);
        pPassword = (EditText) findViewById(R.id.pPassword);

        btVerbinden.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //DB db = new DB(ptIpadress.getText().toString(), ptUSR.getText().toString(), pPassword.getText().toString());
                DB db = new DB();
                if(db.verbinden()){
                    tvStatus.setText("Verbunden");
                    Intent gotoKW = new Intent(MainActivity.this, Kundewaehlen.class);
                    startActivity(gotoKW);
                }else {
                    tvStatus.setText("Nicht Verbunden");
                }

            }

        });
    }
}