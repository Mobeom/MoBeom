package org.tensorflow.lite.examples.classification.Presentation.SplashActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import org.tensorflow.lite.examples.classification.Presentation.MainActivity.MainActivity;
import org.tensorflow.lite.examples.classification.R;

public class SplashActivity extends AppCompatActivity {              // @Copyright for 이원중

    private ViewPager2 mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {              // @Copyright for 이원중
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // sharedPreferences사용하기 위한 변수, 현재는 앱이 처음 실행되는지만 체크
        SharedPreferences sharedPreferences = getSharedPreferences("Prefs", MODE_PRIVATE); // MODE_PRIVATE을 통해 다른 앱에서 접근 불가능하도록 설정

        int num_page = 3;
        FragmentAdapter pagerAdapter = new FragmentAdapter(this, num_page);
        // 첫 접속인지 확인하기 위한 변수
        boolean firstTime = sharedPreferences.getBoolean("firstTime", true);

        mPager = findViewById(R.id.vp2_spalsh); // viewpager
        mPager.setAdapter(pagerAdapter);
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        //mPager.setCurrentItem(0);
        mPager.setOffscreenPageLimit(3);
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {              // @Copyright for 이원중
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {              // @Copyright for 이원중
                super.onPageSelected(position);

            }
        });

        Log.e("firstTime?", String.valueOf(firstTime));

        if (firstTime) { // 첫 접속이라면,
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime", false); // 더이상 첫 접속이 아니므로 firstTime을 false로 변경
            editor.apply();

//            bt_enterApp.setOnClickListener(view -> {
//                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            });

        } else { // 첫 접속이 아니라면 바로 MainActivity로 넘어가도록 설정
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
