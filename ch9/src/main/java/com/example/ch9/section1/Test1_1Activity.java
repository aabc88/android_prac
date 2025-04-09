package com.example.ch9.section1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivityTest11Binding;

public class Test1_1Activity extends AppCompatActivity {
    ActivityTest11Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTest11Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btn1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Sub1Activity.class);
            startActivity(intent);
        });

        binding.btn2.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("ACTION_SUB2");
            //카테고리가 디폴트면 정보를 주지 않아도 기본 적용된다.
            startActivity(intent);
        });

        binding.btn3.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("ACTION_SUB3");
            //카테고리가 디폴트면 정보를 주지 않아도 기본 적용된다.
            intent.addCategory("com.example.ch9.CATEGORY_SUB3");
            startActivity(intent);
        });

        binding.btn4.setOnClickListener(v -> {
            Intent intent = new Intent("ACTION_SUB4", Uri.parse("http://www.naver.com"));
            startActivity(intent);
        });
    }
}