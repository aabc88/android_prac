package com.example.ch4.section2;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;

public class Test2_1Activity extends AppCompatActivity {
    float initX;
    long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test21);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //onBackPressed()함수를 deprecated시키면서 새롭게 제시한 콜백
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 뒤로가기 버튼을 두 번 눌러야 종료
                if (System.currentTimeMillis() - initTime > 3000) {
                    Toast.makeText(Test2_1Activity.this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
                    initTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getRawX();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float diffX = initX - event.getRawX();
            if (diffX > 30) {
                Log.d("ej", "왼쪽으로 화면 밈");
            } else if (diffX < -30) {
                Log.d("ej", "오른쪽으로 화면 밈");
            } else {
                Log.d("ej", "화면을 움직이지 않음");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Log.d("ej", "볼륨 업 버튼 눌림");
        } /*else if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("ej", "볼륨 다운 버튼 눌림");
            // 뒤로가기 버튼을 두 번 눌러야 종료
            if (System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
                initTime = System.currentTimeMillis();
                return true;
            }
        }*/
        return super.onKeyDown(keyCode, event);
    }
}