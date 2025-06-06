package com.example.ch6.section2;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivitySpinnerBinding;
import com.example.ch6.databinding.ActivityTest21Binding;

public class SpinnerActivity extends AppCompatActivity {
    ActivitySpinnerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpinnerBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] data = getResources().getStringArray(R.array.location);
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, data);
        binding.spinner.setAdapter(adapter1);
        binding.autoView.setAdapter(adapter1);
        }
}