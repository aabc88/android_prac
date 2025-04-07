package com.example.ch6.section4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest41Binding;

public class Test4_1Activity extends AppCompatActivity {
    ActivityTest41Binding binding;

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTest41Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //WebView 다양한 설정
        WebSettings webSettings = binding.wv1.getSettings();
        webSettings.setJavaScriptEnabled(true); // 자바스크립트 허용
        webSettings.setDomStorageEnabled(true); // 로컬 스토리지 허용
        webSettings.setDatabaseEnabled(true); // 데이터베이스 허용
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); // 캐시 모드 설정
        webSettings.setAllowFileAccess(true); // 파일 접근 허용
        webSettings.setAllowContentAccess(true); // 콘텐츠 접근 허용
        webSettings.setSupportZoom(true); // 줌 지원
        webSettings.setBuiltInZoomControls(true); // 내장 줌 컨트롤 허용
        webSettings.setDisplayZoomControls(false); // 줌 컨트롤 표시 안 함

        binding.wv1.loadUrl("file:///android_asset/test.html");

        binding.btn1.setOnClickListener(v -> {
            binding.wv1.loadUrl("javascript:lineChart()");
        });

        binding.btn2.setOnClickListener(v -> {
            binding.wv1.loadUrl("javascript:barChart()");
        });
        //js > java 의 함수 호출
        //java의 데이터를 획득, js에서 할수 없는 일을 java가 대신 구현하고 그작업이 이루어짐
        //java에서 객체를 공개해야하고 공개된 객체의 함수만 호출이 가능
        //공개한 객체를 js에서 호출할때는 android.객체명.함수명()으로 호출
        binding.wv1.addJavascriptInterface(new JavascriptTest(), "android");
        binding.wv1.setWebViewClient(new MyWebClient());

    }

    class JavascriptTest {
        @JavascriptInterface
        public String getChartData() {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int i = 0; i < 14; i++) {
                sb.append("[" + i + "," + Math.sin(i) + "]");
                if (i < 13)
                    sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    //WebView에서 유저 이벤트 핸들러
    //일반적인 이벤트 핸들러는 보통 인터페이스 구현이지만 함수가 너무 많으며
    //인터페이스 구현이면 쓰지도 않을 함수를 오버라이드 받아야 한다 그래서 클래스로 만들고 필요한것만 오버라이드

    class MyWebClient extends WebViewClient {
        //webView에서 유저가 링크를 클릭했을 때 새로운 html을 띄우려고 하는 순간의 이벤트
        //매개변수 정보로 어느 url로 로딩하려고 하는지 알아내고 못가게 하거나 원하는 데이터를 추가해 로딩함
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Toast.makeText(Test4_1Activity.this, request.getUrl().toString(), Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    //브라우저 자체 이벤트 핸들러, html로딩상대, alert등이 뜨는 순간 등
    class MyWebChrome extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            //js alert > java dialog 스타일로 변경
            Toast.makeText(Test4_1Activity.this, message, Toast.LENGTH_SHORT).show();
            result.confirm(); // alert이 끝났음을 알림
            return true; // alert이 끝났음을 알림
        }
    }
}