package org.tensorflow.lite.examples.classification.Presentation.CheckListActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;

import org.tensorflow.lite.examples.classification.Presentation.MainActivity.MainActivity;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.ClassifierActivity;
import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.databinding.ActivityCheckListBinding;

public class CheckListActivity extends AppCompatActivity {

    private ActivityCheckListBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_list);
        setContentView(binding.getRoot());

        final CheckBox s_cb1 = (CheckBox)findViewById(R.id.symptom1);
        final CheckBox s_cb2 = (CheckBox)findViewById(R.id.symptom2);
        final CheckBox s_cb3 = (CheckBox)findViewById(R.id.symptom3);
        final CheckBox s_cb4 = (CheckBox)findViewById(R.id.symptom4);
        final CheckBox s_cb5 = (CheckBox)findViewById(R.id.symptom5);
        final CheckBox s_cb6 = (CheckBox)findViewById(R.id.symptom6);
        final CheckBox c_cb1 = (CheckBox)findViewById(R.id.contact1);
        final CheckBox c_cb2 = (CheckBox)findViewById(R.id.contact2);
        final CheckBox c_cb3 = (CheckBox)findViewById(R.id.contact3);
        final CheckBox c_cb4 = (CheckBox)findViewById(R.id.contact4);

        Button go_back = (Button)findViewById(R.id.return_to_home);
        Button result = (Button)findViewById(R.id.button_self_check);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
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
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                if(s_cb1.isChecked() == true) warning.show();
                else if(s_cb2.isChecked() == true) warning.show();
                else if(s_cb3.isChecked() == true) warning.show();
                else if(s_cb4.isChecked() == true) warning.show();
                else if(s_cb5.isChecked() == true) warning.show();
                else if(s_cb6.isChecked() == true) warning.show();
                else if(c_cb1.isChecked() == true) warning.show();
                else if(c_cb2.isChecked() == true) warning.show();
                else if(c_cb3.isChecked() == true) warning.show();
                else if(c_cb4.isChecked() == true) warning.show();
                else {
                    AlertDialog.Builder maybe_okay = new AlertDialog.Builder(CheckListActivity.this);
                    maybe_okay.setTitle("코로나가 의심되지 않습니다");
                    maybe_okay.setMessage("그래도 방역에 노력해주세요");
                    maybe_okay.setIcon(R.drawable.ic_masks_24);
                    maybe_okay.setPositiveButton("넵", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    maybe_okay.show();
                }
            }
        });
    }
}
