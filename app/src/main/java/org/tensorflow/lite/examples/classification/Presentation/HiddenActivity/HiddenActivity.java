package org.tensorflow.lite.examples.classification.Presentation.HiddenActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityHiddenBinding;

public class HiddenActivity extends AppCompatActivity {
    private ActivityHiddenBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hidden);
        setContentView(binding.getRoot());

        binding.clDongwoo.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DongWooE"));
            startActivity(intent);
        });
        binding.clSeongjin.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/L-o-g-a-n"));
            startActivity(intent);
        });
        binding.clGyubbin.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/gyub99"));
            startActivity(intent);
        });
        binding.clWonjoong.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/WonJoongLee"));
            startActivity(intent);
        });
    }
}
