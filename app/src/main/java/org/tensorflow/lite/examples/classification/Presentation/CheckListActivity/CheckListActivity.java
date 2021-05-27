package org.tensorflow.lite.examples.classification.Presentation.CheckListActivity;

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

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;

import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.LoadingActivity;
import org.tensorflow.lite.examples.classification.Presentation.MainActivity.MainActivity;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.ClassifierActivity;
import org.tensorflow.lite.examples.classification.Presentation.MyInfoActivity.MyInfoActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityCheckListBinding;

import java.util.Calendar;

public class CheckListActivity extends AppCompatActivity {

    static public boolean codiv19 = false;
    static public boolean vaccined = false;
    static public String what_vaccined = "";
    static public String first_date_vaccined = "";
    static public String second_date_vaccined = "";

    private ActivityCheckListBinding binding;
    private DatePickerDialog.OnDateSetListener callbackMethod1;
    private DatePickerDialog.OnDateSetListener callbackMethod2;
    final Calendar cal = Calendar.getInstance();

    public CheckListActivity() {
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_list);
        setContentView(binding.getRoot());

        this.InitializeListener1();
        this.InitializeListener2();

        binding.returnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckListActivity.this.finish();
            }
        });
        binding.buttonSelfCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder warning = new AlertDialog.Builder(CheckListActivity.this);
                warning.setTitle("코로나가 의심됩니다");
                warning.setMessage("인근 보건소 위치를 알려드릴까요?");
                warning.setIcon(R.drawable.ic_masks_24);
                warning.setPositiveButton("보러갈래", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
                        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(CheckListActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0); }
                        else  startActivity(intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                });
                warning.setNegativeButton("결과나 보여줘", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), MyInfoActivity.class);
                        startActivity(intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                });
                if (binding.vaccineYes.isChecked()) {vaccined = true;}
                else {vaccined = false;}
                if (binding.symptom1.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.symptom2.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.symptom3.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.symptom4.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.symptom5.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.symptom6.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.contact1.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.contact2.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.contact3.isChecked()) {codiv19 = true; warning.show();}
                else if (binding.contact4.isChecked()) {codiv19 = true; warning.show();}
                else {
                    AlertDialog.Builder maybe_okay = new AlertDialog.Builder(CheckListActivity.this);
                    maybe_okay.setTitle("코로나가 의심되지 않습니다");
                    maybe_okay.setMessage("그래도 방역에 노력해주세요.\n 결과를 보여드릴까요?");
                    maybe_okay.setIcon(R.drawable.ic_masks_24);
                    codiv19 = false;
                    maybe_okay.setPositiveButton("넵", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MyInfoActivity.class);
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                    });
                    maybe_okay.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CheckListActivity.this.finish();
                        }
                    });
                    maybe_okay.show();
                }
                if (binding.vaccineAstra.isChecked()) {what_vaccined = "아스트라제네카";}
                else if (binding.vaccineModerna.isChecked()) {what_vaccined = "모더나";}
                else if (binding.vaccinePfizer.isChecked()) {what_vaccined = "화이자";}
                else if (binding.vaccineJassen.isChecked()) {what_vaccined = "얀센";}
                else what_vaccined = "";
            }
        });
        binding.vaccineNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vaccined = false;
                what_vaccined = "";
                first_date_vaccined = "";
                second_date_vaccined = "";
                for(int i = 0; i < binding.vaccine2Answer.getChildCount(); i++) {
                    ((RadioButton) binding.vaccine2Answer.getChildAt(i)).setEnabled(false);
                }
                binding.first.setEnabled(false);
                binding.second.setEnabled(false);
            }
        });
        binding.vaccineYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vaccined = true;
                    for(int i = 0; i < binding.vaccine2Answer.getChildCount(); i++) {
                        ((RadioButton) binding.vaccine2Answer.getChildAt(i)).setEnabled(true);
                    }
                    binding.first.setEnabled(true);
                    binding.second.setEnabled(true);
            }
        });
    }
    public void InitializeListener1() {
        callbackMethod1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                first_date_vaccined = year + "년 " + (month+1) + "월 " + day + "일";
                binding.firstDate.setText(first_date_vaccined);
            }
        };
    }
    public void InitializeListener2() {
        callbackMethod2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                second_date_vaccined=year + "년 " + (month+1) + "월 " + day + "일";
                binding.secondDate.setText(second_date_vaccined);
            }
        };
    }
    public void OnClickHandler1(View view) {
        DatePickerDialog dialog1 = new DatePickerDialog(this, callbackMethod1, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog1.show();
    }
    public void OnClickHandler2(View view) {
        DatePickerDialog dialog2 = new DatePickerDialog(this, callbackMethod2, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog2.show();
    }
}
