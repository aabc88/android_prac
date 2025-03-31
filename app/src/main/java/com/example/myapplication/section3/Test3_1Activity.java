package com.example.myapplication.section3;

import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTest31Binding;

public class Test3_1Activity extends AppCompatActivity {
    Button visivleButton;
    Button invisibleButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test31);
        ActivityTest31Binding binding = ActivityTest31Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        visivleButton = findViewById(R.id.btn_visible);
        invisibleButton = findViewById(R.id.btn_invisible);
        textView = findViewById(R.id.tv_test1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ----------------------------
        // Using findViewById

        visivleButton.setOnClickListener(v -> {
            textView.setVisibility(View.VISIBLE);
        });

        invisibleButton.setOnClickListener(v -> {
            textView.setVisibility(View.INVISIBLE);
        });

        // ----------------------------
        // Using View Binding

        binding.btnVisible.setOnClickListener(v -> {
            binding.tvTest1.setVisibility(View.VISIBLE);
        });

        binding.btnInvisible.setOnClickListener(v -> {
            binding.tvTest1.setVisibility(View.INVISIBLE);
        });
    }
}