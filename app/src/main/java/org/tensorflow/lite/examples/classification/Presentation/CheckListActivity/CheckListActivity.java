package org.tensorflow.lite.examples.classification.Presentation.CheckListActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityCheckListBinding;

public class CheckListActivity extends AppCompatActivity {

    private ActivityCheckListBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_list);
        setContentView(binding.getRoot());
    }
}
