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


public class MyInfoActivity extends AppCompatActivity {     //@copyright for 김규빈

    private ActivityMyinfoBinding binding;

    public MyInfoActivity() {       //@copyright for 김규빈
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {      //@copyright for 김규빈
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myinfo);
        setContentView(binding.getRoot());

        if (CheckListActivity.codiv19 == true) {binding.myCodiv19.setText("O");}
        else {binding.myCodiv19.setText("X");}

        if (CheckListActivity.vaccined == true) {binding.myGetVaccine.setText("O");}
        else {binding.myGetVaccine.setText("X");}

        if (CheckListActivity.what_vaccined != "") {binding.myWhatVaccine.setText(CheckListActivity.what_vaccined);}
        else {binding.myWhatVaccine.setText("-");}

        if (CheckListActivity.first_date_vaccined != "") {binding.myFirstVaccine.setText(CheckListActivity.first_date_vaccined);}
        else {binding.myFirstVaccine.setText("-");}

        if (CheckListActivity.second_date_vaccined != "") {binding.mySecondVaccine.setText(CheckListActivity.second_date_vaccined);}
        else {binding.mySecondVaccine.setText("-");}

        binding.returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyInfoActivity.this.finish();
            }
        });
    }
}

