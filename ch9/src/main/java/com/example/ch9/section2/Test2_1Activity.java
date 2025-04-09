package com.example.ch9.section2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivityTest21Binding;

public class Test2_1Activity extends AppCompatActivity {
        ActivityTest21Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTest21Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                //요청의 실행자.. RequestPermission, 인텐트발생(StartActivityForResult)
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String data1 = intent.getStringExtra("result1");
                        int data2 = intent.getIntExtra("result2", 0);
                        binding.tvResultView.setText("data1 : " + data1 + "\n" + "data2 : " + data2);
                    }
                }
        );
        binding.btn1.setOnClickListener(v -> {
            Intent intent = new Intent(this, SomeActivity.class);
            intent.putExtra("data1", "data1");
            intent.putExtra("data2", 200);
            //startActivity(intent);
            launcher.launch(intent);
        });
    }
}