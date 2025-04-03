package com.example.ch5.section3;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowMetrics;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch5.R;
import com.example.ch5.databinding.ActivityTest31Binding;

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

        //앱이 설치 된 폰 사이즈 확인
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = getWindowManager().getCurrentWindowMetrics();
            int width = windowMetrics.getBounds().width();
            int height = windowMetrics.getBounds().height();
            Log.d("Test3_1Activity", "width: " + width + ", height: " + height);
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
        }

        //유저 폰의 밀도
        float density = getResources().getDisplayMetrics().density;

        //코드에서 사이즈 지정할 때는 단위 추가 가능
        //코드에서 지정하는 사이즈는 px이다
        //폰사이즈 호환성을 고려해서 코드에서 사이즈 지정할 때 density값을 얻어서 계산함
        /*binding.btn1.getLayoutParams().width = 100;
        binding.btn2.getLayoutParams().width = (int) (100 * density);*/
    }
}