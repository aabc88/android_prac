package com.example.ch9.section3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.os.ConfigurationCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivityTest31Binding;

public class Test3_1Activity extends AppCompatActivity {
    ActivityTest31Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTest31Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                ifGranted -> {
                    if (ifGranted) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-1234-1234"));
                        startActivity(intent);
                    } else {
                        // 권한이 거부된 경우
                        // 권한 요청을 다시 하거나, 사용자에게 알림을 표시할 수 있습니다.
                        Toast.makeText(this, "권한을 허용해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        binding.btn1.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(this, "android.permission.CALL_PHONE") == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-1234-1234"));
                startActivity(intent);
            } else {
                // 권한이 없으므로 요청
                launcher.launch("android.permission.CALL_PHONE");
            }

        });

        binding.btn2.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5665,126.978"));
            startActivity(intent);
        });

        binding.btn3.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(("https://www.naver.com")));
            startActivity(intent);
        });

    }
}