package com.example.ch3.section3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch3.R;
import com.example.ch3.databinding.ActivityTest31Binding;

public class Test3_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTest31Binding binding = ActivityTest31Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btn1.setOnClickListener(v -> {
            binding.tv1.setVisibility(TextView.VISIBLE);
            binding.tv2.setVisibility(TextView.INVISIBLE);
            binding.tv3.setVisibility(TextView.INVISIBLE);
        });

        binding.btn2.setOnClickListener(v -> {
            binding.tv1.setVisibility(TextView.INVISIBLE);
            binding.tv2.setVisibility(TextView.VISIBLE);
            binding.tv3.setVisibility(TextView.INVISIBLE);
        });

        binding.btn3.setOnClickListener(v -> {
            binding.tv1.setVisibility(TextView.INVISIBLE);
            binding.tv2.setVisibility(TextView.INVISIBLE);
            binding.tv3.setVisibility(TextView.VISIBLE);
        });
    }
}