package com.mobeom.mobeom.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobeom.mobeom.MainActivity;
import com.mobeom.mobeom.R;

public class SplashScreen extends AppCompatActivity {

    private ViewPager2 mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // sharedPreferences사용하기 위한 변수, 현재는 앱이 처음 실행되는지만 체크
        SharedPreferences sharedPreferences = getSharedPreferences("Prefs", MODE_PRIVATE); // MODE_PRIVATE을 통해 다른 앱에서 접근 불가능하도록 설정
        // 첫화면 끝나고 MainActivity로 넘어가기 위한 버튼
        //Button bt_enterApp = findViewById(R.id.bt_enterApp);
        int num_page = 4;
        FragmentAdapter pagerAdapter = new FragmentAdapter(this, num_page);
        // 첫 접속인지 확인하기 위한 변수
        boolean firstTime = sharedPreferences.getBoolean("firstTime", true);

        mPager = findViewById(R.id.vp2_splashViewPager); // viewpager
        mPager.setAdapter(pagerAdapter);
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        //mPager.setCurrentItem(0);
        mPager.setOffscreenPageLimit(3);
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });

        if(firstTime){ // 첫 접속이라면,
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime", false); // 더이상 첫 접속이 아니므로 firstTime을 false로 변경
            editor.apply();

//            bt_enterApp.setOnClickListener(view -> {
//                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            });

        }else{ // 첫 접속이 아니라면 바로 MainActivity로 넘어가도록 설정
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}