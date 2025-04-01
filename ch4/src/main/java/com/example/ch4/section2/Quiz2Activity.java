package com.example.ch4.section2;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;
import com.example.ch4.databinding.ActivityQuiz2Binding;

public class Quiz2Activity extends AppCompatActivity {
    ActivityQuiz2Binding binding;
    long initTime;
    long pauseTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuiz2Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnStart.setOnClickListener(new EventHandler());
        binding.btnStop.setOnClickListener(new EventHandler());
        binding.btnReset.setOnClickListener(new EventHandler());

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 뒤로가기 버튼을 두 번 눌러야 종료
                if (System.currentTimeMillis() - initTime > 3000) {
                    Toast.makeText(Quiz2Activity.this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
                    initTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
        });
    }

    class EventHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == binding.btnStart) {
                Log.d("ej", "onClick: btnStart");
                binding.btnStop.setEnabled(true);
                binding.btnStart.setEnabled(false);
                binding.chronometer.setBase(SystemClock.elapsedRealtime() + pauseTime);
                binding.chronometer.start();
            } else if (v == binding.btnStop) {
                Log.d("ej", "onClick: btnStop");
                binding.btnStop.setEnabled(false);
                binding.btnStart.setEnabled(true);
                pauseTime = binding.chronometer.getBase() - SystemClock.elapsedRealtime();
                binding.chronometer.stop();
            } else if (v == binding.btnReset) {
                Log.d("ej", "onClick: btnReset");
                pauseTime = 0;
                binding.chronometer.stop();
                binding.chronometer.setBase(SystemClock.elapsedRealtime());
                binding.btnStop.setEnabled(false);
                binding.btnStart.setEnabled(true);
            }
        }
    }
}