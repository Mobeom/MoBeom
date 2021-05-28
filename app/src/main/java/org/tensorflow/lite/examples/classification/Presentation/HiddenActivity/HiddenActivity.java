package org.tensorflow.lite.examples.classification.Presentation.HiddenActivity;

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
    }
}
