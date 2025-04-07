package com.example.ch6.section6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

//주가를 출력하는 텍스트뷰 개발
//주가 데이터만 지정되면 알아서 빨강 차랑으로 출력할지 결정
//TextView는 직접 상속이 안되고 동일한 AppCompatTextView를 상속받아야 함
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    //만들어진 뷰를 activity 개발자가 xml파일에 등록해서 사용한다면
    //inflate시 상황에 따라 호출되는 생성자가 다르다
    //가급적 생성자를 모두 준비해야함.
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            int value = Integer.parseInt(getText().toString());
            if (value < 0) {
                setTextColor(Color.RED);
            } else {
                setTextColor(Color.GREEN);
            }
            super.onDraw(canvas);
        } catch (Exception e) {
            super.onDraw(canvas);
        }
    }
}
