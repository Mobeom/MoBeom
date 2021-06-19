package org.tensorflow.lite.examples.classification.Presentation.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import org.tensorflow.lite.examples.classification.Data.API.CoronaAPI;
import org.tensorflow.lite.examples.classification.Data.API.CoronaStatus;
import org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.CheckListActivity;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.LoadingActivity;
import org.tensorflow.lite.examples.classification.Presentation.HiddenActivity.HiddenActivity;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.ClassifierActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityMainBinding;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {              // @Copyright for 이원중

    private ActivityMainBinding binding;
    private int maskClickCnt = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {              // @Copyright for 이원중
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(binding.getRoot());

        // 각 버튼 클릭 리스너들
        maskDetectorClickListener();
        selfCheckClickListener();
        healthCenterClickListener();
        maskClickListener();

        new Thread(() -> {
            try {
                Date currentTime = Calendar.getInstance().getTime();
                Date yesterdayTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
                CustomDate cd = getDate(currentTime, yesterdayTime);
                CoronaStatus cs = CoronaAPI.getStatus(cd.yesterdayDate, cd.todayDate, getBaseContext());
                System.out.println("이거 -> " + cs.getDeathCnt());
                runOnUiThread(() -> {
                    binding.tvDeathCnt.setText(cs.getDeathCnt() + "명");
                    binding.tvDecideStatusNum.setText(cs.getDecideCnt() + "명");
                    binding.tvClrStatusNum.setText(cs.getClrCnt() + "명");

                    binding.pgbarClrStatus.setVisibility(View.GONE);
                    binding.tvClrStatusNum.setVisibility(View.VISIBLE);

                    binding.pgbarDecideStatus.setVisibility(View.GONE);
                    binding.tvDecideStatusNum.setVisibility(View.VISIBLE);

                    binding.pgbarDeathCnt.setVisibility(View.GONE);
                    binding.tvDeathCnt.setVisibility(View.VISIBLE);
                });
            } catch (IOException e) {
                Log.e("IOException", e.toString());
            } catch (XmlPullParserException e) {
                Log.e("XmlPullParserException", e.toString());
                e.printStackTrace();
            }
        }).start();
    }

    private void maskDetectorClickListener() {              // @Copyright for 이원중
        binding.buttonMaskDetector.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ClassifierActivity.class);
            startActivity(intent);
        });
    }

    private void selfCheckClickListener() {              // @Copyright for 이원중
        binding.buttonSelfCheck.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CheckListActivity.class);
            startActivity(intent);
        });
    }

    private void healthCenterClickListener() {              // @Copyright for 이원중
        binding.buttonHealthCenter.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
            if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            } else startActivity(intent);
        });
    }

    private void maskClickListener() {              // @Copyright for 이원중
        binding.ivMask.setOnClickListener(view -> {
            maskClickCnt++;
            if (maskClickCnt >= 5) {
                maskClickCnt = 0;
                Intent intent = new Intent(getApplicationContext(), HiddenActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * API 형식에 맞춰서 date 값을 반환해주는 함수입니다.
     */
    private CustomDate getDate(Date currentTime, Date yesterdayTime) {              // @Copyright for 이원중
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

    private static class CustomDate {              // @Copyright for 이원중
        private final String todayDate;
        private final String yesterdayDate;

        public CustomDate(String todayDate, String yesterdayDate) {
            this.todayDate = todayDate;
            this.yesterdayDate = yesterdayDate;
        }
    }
}
