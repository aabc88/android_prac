package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액티비티의 background가 시스템영역까지 보일 수 있도록 설정
        EdgeToEdge.enable(this);
        //화면출력명령, R - 리소스
        setContentView(R.layout.activity_main);
        //액티비티의 내용이 화면에 출력될 때
        //디바이스의 특징에 의해 보이지 않게 되는 부분을 최대한으로 보여주기 위해
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}