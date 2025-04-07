package com.example.ch6.section5;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest51Binding;

public class Test5_1Activity extends AppCompatActivity {
    ActivityTest51Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTest51Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        binding.sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.tv1.setText("progress: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                binding.tv1.setText("onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                binding.tv1.setText("onStopTrackingTouch");
            }
        });

        ProgressThread progressThread = new ProgressThread();
        progressThread.start();
    }

    class ProgressThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                binding.sb1.setProgress(i);
                try {
                    Thread.sleep(100);
                    binding.pb1.incrementProgressBy(10);
                    binding.pb1.incrementSecondaryProgressBy(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}