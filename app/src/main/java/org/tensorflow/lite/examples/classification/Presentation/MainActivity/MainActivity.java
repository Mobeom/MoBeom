package org.tensorflow.lite.examples.classification.Presentation.MainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.tensorflow.lite.examples.classification.Data.API.CoronaAPI;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.ClassifierActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(binding.getRoot());

        binding.buttonMaskDetector.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ClassifierActivity.class);
            startActivity(intent);
        });



        new Thread(() -> {
            CoronaAPI coronaAPI = new CoronaAPI();
            try{
                CoronaAPI.getStatus();
            }catch (IOException e){
                System.out.println(e.toString());
            }
        }).start();

    }
}
