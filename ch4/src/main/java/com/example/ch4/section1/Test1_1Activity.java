package com.example.ch4.section1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;
import com.example.ch4.databinding.ActivityTest11Binding;

public class Test1_1Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    ActivityTest11Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTest11Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //두 뷰의 이벤트 핸들러를 동일한 클래스 객체로 지정해도 되고 따로 만들어서 지정해도 됨.
        binding.btn1.setOnClickListener(new EventHandler());
        binding.btn2.setOnClickListener(new EventHandler());
        //액티비티 자체가 이벤트 핸들러가 되게
        binding.cb1.setOnCheckedChangeListener(this);
        //이벤트 핸들러가 어디선가 재사용이 되지 않는다고 하면 익명클래스로
        /*binding.sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("ej", "onCheckedChanged: sw1 : "+ isChecked);
            }
        });*/
        //익터페이스를 구현한 익명 클래스를 정의하고 지정할 때 그 인터페이스의 추상함수가 하나라면 아래처럼
        binding.sw1.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            Log.d("ej", "onCheckedChanged: sw1 : "+ isChecked);
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("ej", "onCheckedChanged: cb1 : "+ isChecked);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    class EventHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v == binding.btn1) {
                Log.d("ej", "onClick: 1");
            } else if (v == binding.btn2) {
                Log.d("ej", "onClick: 2");
            }
        }
    }
}