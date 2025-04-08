package com.example.ch7.section1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch7.R;
import com.example.ch7.databinding.ActivityPermissionBinding;

public class PermissionActivity extends AppCompatActivity {
    ActivityPermissionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPermissionBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        // Permission granted
                    } else {
                        // Permission denied
                        Toast.makeText(this, "권한이 거부됨", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        binding.btn1.setOnClickListener(v -> {
            //normal protectionLevel로 선언되어 있으면 AndroidManifest.xml에 <users-permission>선언으로 끝
            Intent intent = new Intent("CH7_ACTION_ONE");
            startActivity(intent);
        });

        binding.btn2.setOnClickListener(v -> {
            //dangerous로 선언되어 있다면 퍼미션 상태 체크하고 거부상태라면 조정 다이얼로그 띄워야함.
            if(checkSelfPermission(this,"com.example.ch7_outer.TWO_PERMISSION") == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                // Permission denied
                launcher.launch("com.example.ch7_outer.TWO_PERMISSION");
            }

            Intent intent = new Intent("CH7_ACTION_TWO");
            startActivity(intent);
        });
    }
}