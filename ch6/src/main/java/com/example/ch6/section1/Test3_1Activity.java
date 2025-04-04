package com.example.ch6.section1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest31Binding;

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
        //문자열 데이터 추가, 필요한 만큼 각 Span객체 추가
        String data = "복수초 \n img \n 이른봄 설산에서 만나는 복수초는 어쩌구...";
        SpannableStringBuilder builder = new SpannableStringBuilder(data);

        int start = data.indexOf("img");
        if (start > -1) {
            int end = start + "img".length();
            Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.img1, null);
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(dr, ImageSpan.ALIGN_BASELINE);
            builder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        start = data.indexOf("복수초");
        if (start > -1) {
            int end = start + "복수초".length();
            StyleSpan styleSpan = new StyleSpan(android.graphics.Typeface.BOLD);
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(2.0f);
            builder.setSpan(styleSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(sizeSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        binding.tv1.setText(builder);

        //html태그 효과를 적용해서 문자열 출력
        //브라우저로 출력하는 것 아님 TextView에
        //내부적으로는 Span을 사용함

        String html = "<font-color='RED'>얼레지</font><br/><img src='img1'/><br/>곰배령...";

        //만약 html내에 <img>만 없다면 매개변수 하나짜리.
        //<img>가 있다면 두번째 매개변수로 MyImageGetter를 넘겨야 함
        binding.tv2.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, new MyImageGetter(), null));

    }

    class MyImageGetter implements Html.ImageGetter {
        @Override
        public Drawable getDrawable(String source) {
            //img1을 찾아서 그려줌
            Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.img2, null);
            if (dr != null) {
                dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            }
            return dr;
        }
    }
}