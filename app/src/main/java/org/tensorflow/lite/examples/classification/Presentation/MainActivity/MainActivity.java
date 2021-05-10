package org.tensorflow.lite.examples.classification.Presentation.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.tensorflow.lite.examples.classification.Data.API.CoronaAPI;
import org.tensorflow.lite.examples.classification.Data.API.CoronaStatus;
import org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.CheckListActivity;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.HealthCenterActivity;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.ClassifierActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityMainBinding;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

        binding.buttonHealthCenter.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HealthCenterActivity.class);
            startActivity(intent);
        });

        new Thread(() -> {
            try {
                Date currentTime = Calendar.getInstance().getTime();
                Date yesterdayTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
                CustomDate cd = getDate(currentTime, yesterdayTime);
                CoronaStatus cs = CoronaAPI.getStatus(cd.yesterdayDate, cd.todayDate);
                System.out.println("이거 -> " + cs.getDeathCnt());
                runOnUiThread(() -> {
                    binding.tvDeathCnt.setText(cs.getDeathCnt() + "명");
                    binding.tvDecideStatusNum.setText(cs.getDecideCnt() + "명");
                    binding.tvClrStatusNum.setText(cs.getClrCnt() + "명");
                });
            } catch (IOException e) {
                System.out.println(e.toString());
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * API 형식에 맞춰서 date 값을 반환해주는 함수입니다.
     */
    private CustomDate getDate(Date currentTime, Date yesterdayTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        String todayYear = yearFormat.format(currentTime);
        String yesterdayYear = yearFormat.format(yesterdayTime);
        String todayMonth = monthFormat.format(currentTime);
        String yesterdayMonth = monthFormat.format(yesterdayTime);
        String todayDay = dateFormat.format(currentTime);
        String yesterdayDay = dateFormat.format(yesterdayTime);
        String todayInFormat = todayYear + todayMonth + todayDay;
        String yesterdayInFormat = yesterdayYear + yesterdayMonth + yesterdayDay;
        return new CustomDate(todayInFormat, yesterdayInFormat);
    }


    private static class CustomDate {
        private final String todayDate;
        private final String yesterdayDate;

        public CustomDate(String todayDate, String yesterdayDate) {
            this.todayDate = todayDate;
            this.yesterdayDate = yesterdayDate;
        }
    }
}
