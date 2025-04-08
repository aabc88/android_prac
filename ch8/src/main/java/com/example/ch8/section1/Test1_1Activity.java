package com.example.ch8.section1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch8.R;
import com.example.ch8.databinding.ActivityTest11Binding;

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

        binding.btn1.setOnClickListener(v ->{
            Intent intent = new Intent(this, Sub1Activity.class);
            startActivity(intent);
        });

        binding.btn2.setOnClickListener(v ->{
            Intent intent = new Intent(this, Sub2Activity.class);
            startActivity(intent);
        });
    }
}