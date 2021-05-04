package org.tensorflow.lite.examples.classification.Presentation.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.tensorflow.lite.examples.classification.Data.API.CoronaAPI;
import org.tensorflow.lite.examples.classification.Data.API.CoronaStatus;
import org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.CheckListActivity;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.ClassifierActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityMainBinding;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(binding.getRoot());

        binding.buttonMaskDetector.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ClassifierActivity.class);
            startActivity(intent);
        });

        binding.buttonSelfCheck.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CheckListActivity.class);
            startActivity(intent);
        });


        new Thread(() -> {
            try{
                CoronaStatus cs = CoronaAPI.getStatus();
                System.out.println("이거 -> " + cs.getDeathCnt());
                runOnUiThread(() -> binding.tvDeathCnt.setText(cs.getDeathCnt()+"명"));
            }catch (IOException e){
                System.out.println(e.toString());
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
