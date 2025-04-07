package com.example.ch6.section6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ch6.R;

import org.w3c.dom.Attr;

import java.util.ArrayList;

//custom view 개발자 입장의 코드
//동일한 화면을 출력하는 api뷰가 없다. 일일히 그려하함
//최상위 View 상속으로 개발
public class MyPlusMinusView extends View {
    Context context;
    int value;
    Bitmap plusBitmap;
    Bitmap minusBitmap;
    Rect plusRect;
    Rect minusRect;
    int textColor;

    ArrayList<OnMyChangeListener> listeners;

    private void init(AttributeSet attrs) {
        plusBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plus);
        minusBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.minus);
        plusRect = new Rect(10, 10, 210, 210);
        minusRect = new Rect(400, 10, 600, 210);

        if (attrs != null) {
            //액티비티에서 지정한 속성값 추출
            @SuppressLint("Recycle") TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
            textColor = array.getColor(R.styleable.MyView_customTextColor, Color.RED);
        }

        listeners = new ArrayList<>();
    }

    //액티비티에서 이벤트 핸들러 등록하기 위해 호출하는 함수
    public void setOnMyChangeListener(OnMyChangeListener listener) {
        listeners.add(listener);
    }

    //뷰의 사이즈를 결정하기 위해 자동호충
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //액티비티 개발자가 지정한 사이즈값을 참조해서 계싼한다.
        //액티비티에서 설정한 사이즈 정보 획득
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        if (widthMode == MeasureSpec.AT_MOST) {
            width = 700;
        } else if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            height = 250;
        } else if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }

        //어떻게 사이즈를 결정하든 최종 setMeasureDimension(width, height) 호출해서 알려준 사이즈가 뷰의 사이즈가 된다.
        setMeasuredDimension(width, height);
    }

    //뷰의 이벤트 처리 이 뷰에서 발생하는 터치 이벤트 처리


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        //터치한 지점이 plus ? minus ?
        if (plusRect.contains(x, y) && event.getAction() == MotionEvent.ACTION_DOWN) {
            value++;
            invalidate();//뷰의 화면이 다시 그려져야 한다는 신호 이렇게 되면 onDraw()가 호출된다.
            //액티비티의 이벤트 핸들러도 실행.
            for (OnMyChangeListener listener : listeners) {
                listener.onChange(value);
            }
            return true;
        } else if (minusRect.contains(x, y) && event.getAction() == MotionEvent.ACTION_DOWN) {
            value--;
            invalidate();
            for (OnMyChangeListener listener : listeners) {
                listener.onChange(value);
            }
            return true;
        }
        return false;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        //뷰 전체를 지정한 색으로 칠함
        canvas.drawColor(Color.alpha(Color.CYAN));
        Rect plusR = new Rect(0, 0, plusBitmap.getWidth(), plusBitmap.getHeight());
        Rect minusR = new Rect(0, 0, minusBitmap.getWidth(), minusBitmap.getHeight());

        Paint paint = new Paint();

        canvas.drawBitmap(plusBitmap, plusR, plusRect, paint);

        paint.setTextSize(80);
        paint.setColor(textColor);
        canvas.drawText(String.valueOf(value), 260, 150, paint);
        canvas.drawBitmap(minusBitmap, minusR, minusRect, null);
    }

    public MyPlusMinusView(Context context) {
        super(context);
        this.context = context;
        init(null);
    }

    public MyPlusMinusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public MyPlusMinusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }
}
