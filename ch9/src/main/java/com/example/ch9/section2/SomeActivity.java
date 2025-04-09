package com.example.ch9.section2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivitySomeBinding;

public class SomeActivity extends AppCompatActivity {
    ActivitySomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySomeBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        //넘어온 extra데이터 추출
        String data1 = intent.getStringExtra("data1");
        int data2 = intent.getIntExtra("data2", 0);

        binding.tvExtraData.setText("data1 : " + data1 + "\n" + "data2 : " + data2);

        binding.btn1.setOnClickListener(v ->{
            Intent intent1 = new Intent();
            intent1.putExtra("result1", "result data1");
            intent1.putExtra("result2", 100);
            setResult(RESULT_OK, intent1);
            finish();
        });

    }
}