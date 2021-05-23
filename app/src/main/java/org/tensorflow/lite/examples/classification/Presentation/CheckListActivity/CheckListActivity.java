package org.tensorflow.lite.examples.classification.Presentation.CheckListActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import org.tensorflow.lite.examples.classification.Presentation.MainActivity.MainActivity;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.ClassifierActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityCheckListBinding;

import java.util.Calendar;

public class CheckListActivity extends AppCompatActivity {

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
                warning.setPositiveButton("보러갈래", null);
                warning.setNegativeButton("그정도는..", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CheckListActivity.this.finish();
                    }
                });
                if (binding.symptom1.isChecked()) warning.show();
                else if (binding.symptom2.isChecked()) warning.show();
                else if (binding.symptom3.isChecked()) warning.show();
                else if (binding.symptom4.isChecked()) warning.show();
                else if (binding.symptom5.isChecked()) warning.show();
                else if (binding.symptom6.isChecked()) warning.show();
                else if (binding.contact1.isChecked()) warning.show();
                else if (binding.contact2.isChecked()) warning.show();
                else if (binding.contact3.isChecked()) warning.show();
                else if (binding.contact4.isChecked()) warning.show();
                else {
                    AlertDialog.Builder maybe_okay = new AlertDialog.Builder(CheckListActivity.this);
                    maybe_okay.setTitle("코로나가 의심되지 않습니다");
                    maybe_okay.setMessage("그래도 방역에 노력해주세요");
                    maybe_okay.setIcon(R.drawable.ic_masks_24);
                    maybe_okay.setPositiveButton("넵", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CheckListActivity.this.finish();
                        }
                    });
                    maybe_okay.show();
                }
            }
        });
        binding.vaccineNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.vaccine2.setVisibility(View.GONE);
                binding.vaccine2Answer.setVisibility(View.GONE);
                binding.vaccine3.setVisibility(View.GONE);
                binding.first.setVisibility(View.GONE);
                binding.second.setVisibility(View.GONE);
            }
        });
        binding.vaccineYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.vaccine2.setVisibility(View.VISIBLE);
                binding.vaccine2Answer.setVisibility(View.VISIBLE);
                binding.vaccine3.setVisibility(View.VISIBLE);
                binding.first.setVisibility(View.VISIBLE);
                binding.second.setVisibility(View.VISIBLE);
            }
        });
    }
    public void InitializeListener1() {
        callbackMethod1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                binding.firstDate.setText(year + "년 " + (month+1) + "월 " + day + "일");
            }
        };
    }
    public void InitializeListener2() {
        callbackMethod2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                binding.secondDate.setText(year + "년 " + (month+1) + "월 " + day + "일");
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
