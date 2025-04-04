package com.example.ch6.section1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityListviewTest11Binding;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewTest1_1Activity extends AppCompatActivity {
    ActivityListviewTest11Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListviewTest11Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] data = getResources().getStringArray(R.array.location);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        binding.lv1.setAdapter(arrayAdapter);
        binding.lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //position : 선택 한 항목의 index
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewTest1_1Activity.this, "선택한 항목 : " + data[position], Toast.LENGTH_SHORT).show();
            }
        });

        //SimpleAdapter 한항목에 여러건의 데이터가 순차적으로
        //SimpleAdapter에 데이터 구성이 ArrayList<Map<String, String>> 형태로 되어야 함
        ArrayList<HashMap<String, String>> simpleData = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", data[i]);
            map.put("content", String.valueOf(i * 10));
            simpleData.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simpleData,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "content"},//항목의 데이터가 map, map에서 데이터를 추출할 때 사용할 key
                new int[]{android.R.id.text1, android.R.id.text2}

        );

        binding.lv2.setAdapter(simpleAdapter);
    }
}