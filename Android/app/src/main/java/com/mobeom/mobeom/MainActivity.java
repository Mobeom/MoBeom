package com.mobeom.mobeom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_title;
    Button bt_manual;
    Button bt_maskDetector;
    Button bt_autoDiagnosis;
    Button bt_exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_title = findViewById(R.id.tv_title);
        bt_manual = findViewById(R.id.bt_manual);
        bt_maskDetector = findViewById(R.id.bt_maskDetector);
        bt_autoDiagnosis = findViewById(R.id.bt_autoDiagnosis);
        bt_exit = findViewById(R.id.bt_exit);


        bt_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });

        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}