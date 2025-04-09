package com.example.ch8.section2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch8.R;
import com.example.ch8.databinding.ActivityTest21Binding;

public class Test2_1Activity extends AppCompatActivity {
    ActivityTest21Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTest21Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    //액티비티의 메뉴 구성 함수, 액티비티 초기화 되면서 한번 호출

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*menu.add(0, 0, 0, "Menu1");
        menu.add(0, 1, 0, "Menu2");*/

        //resource xml로 구성
        getMenuInflater().inflate(R.menu.menu_test, menu);
        MenuItem menuItem = menu.findItem(R.id.menu3);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //검색을 위해 유저가 키보드의 검색버튼을 클릭한 순간.
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Test2_1Activity.this, "submit: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(Test2_1Activity.this, "change: " + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu1) {
            Toast.makeText(this, "menu 1 click", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu2) {
            Toast.makeText(this, "menu 2 click", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}