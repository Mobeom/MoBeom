package org.tensorflow.lite.examples.classification.Presentation.MyInfoActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.DatePicker;

import org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.CheckListActivity;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.LoadingActivity;
import org.tensorflow.lite.examples.classification.Presentation.MainActivity.MainActivity;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.ClassifierActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityMyinfoBinding;

import java.util.Calendar;

public class MyInfoActivity extends AppCompatActivity {

    private ActivityMyinfoBinding binding;

    public MyInfoActivity() {
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myinfo);
        setContentView(binding.getRoot());

        binding.returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyInfoActivity.this.finish();
            }
        });
    }
}

