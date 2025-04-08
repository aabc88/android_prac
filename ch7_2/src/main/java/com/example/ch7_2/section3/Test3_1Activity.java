package com.example.ch7_2.section3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch7_2.R;
import com.example.ch7_2.databinding.ActivityTest31Binding;
import com.example.ch7_2.databinding.DialogTestBinding;

import java.util.ArrayList;

public class Test3_1Activity extends AppCompatActivity {
    ActivityTest31Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTest31Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //dialog의 버튼 클릭 이벤트 핸들러.
        //이벤트 핸들러를 등록하지 않아도 dialog버튼을 클릭하면 자동으로 닫김.
        DialogInterface.OnClickListener dialogHandler = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String msg = "";
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    msg = "positive";
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    msg = "negative";
                } else if (which == DialogInterface.BUTTON_NEUTRAL) {
                    msg = "neutral";
                }
                Toast.makeText(Test3_1Activity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };

        binding.btn1.setOnClickListener(v -> {
            //AlertDialog를 생성하는 Builder객체를 생성.
            //setter메서드를 사용하여 AlertDialog의 제목, 메시지, 버튼을 설정.
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("title")
                    .setMessage("message")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("positive", dialogHandler)
                    .setNegativeButton("negative", dialogHandler)
                    .setNeutralButton("neutral", dialogHandler)
                    .show();
        });

        String[] arrays = getResources().getStringArray(R.array.list);

        binding.btn2.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("list dialog");
            builder.setItems(R.array.list, (dialog, witch) -> {
                //항목을 선택할 때 이벤트
                String msg = arrays[witch];
                Toast.makeText(Test3_1Activity.this, msg, Toast.LENGTH_SHORT).show();
            });
            builder.show();
        });

        boolean[] selectedItem = new boolean[arrays.length];

        binding.btn3.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("항목을 선택하세요");
            builder.setMultiChoiceItems(arrays, selectedItem, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    //항목을 선택할 때 이벤트
                    String msg = arrays[which];
                    Toast.makeText(Test3_1Activity.this, msg + " " + isChecked, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setPositiveButton("확인", (dialog, which) -> {
                ArrayList<String> selectedOption = new ArrayList<>();
                for (int i = 0; i < selectedItem.length; i++) {
                    if (selectedItem[i]) {
                        selectedOption.add(arrays[i]);
                    }
                }
                Toast.makeText(this, selectedOption.toString(), Toast.LENGTH_SHORT).show();
            });
            builder.show();
        });


        binding.btn4.setOnClickListener(v -> {
            //custom dialog를 위한 layout xml을 inflate시켜 dialog에 설정.
            DialogTestBinding dialogBinding = DialogTestBinding.inflate(getLayoutInflater());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogBinding.getRoot());
            builder.setNegativeButton("cancel", (dialog, which) -> {
                //dialog를 닫는 이벤트
                dialog.dismiss();
            });
            builder.show();
        });
    }
}