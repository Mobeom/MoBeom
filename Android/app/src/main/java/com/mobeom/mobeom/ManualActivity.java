package com.mobeom.mobeom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ManualActivity extends AppCompatActivity {

    TextView tv_manual_title;
    Button bt_manual_maskDetector;
    Button bt_manual_checker;
    Button bt_manual_finder;
    Button bt_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        tv_manual_title = findViewById(R.id.tv_manual_title);
        bt_manual_maskDetector = findViewById(R.id.bt_manual_maskDetector);
        bt_manual_checker = findViewById(R.id.bt_manual_checker);
        bt_manual_finder = findViewById(R.id.bt_manual_finder);
        bt_return = findViewById(R.id.bt_return);

        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManualActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}