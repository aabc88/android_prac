package com.example.ch10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch10.databinding.ActivityTest2Binding;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTest2Binding binding = ActivityTest2Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnInsert.setOnClickListener(v -> {
            String name = binding.etName.getText().toString();
            String address = binding.etAddress.getText().toString();
            SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("address", address);

            db.insert("tb_user", null, values);
            db.close();
        });

        binding.btnQuery.setOnClickListener(v -> {
            SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
            Cursor cursor = db.query("tb_user", null, null, null, null, null, null);
            String result = "";
            while (cursor.moveToNext()) {
                result += cursor.getString(1);
                result += " : ";
                result += cursor.getString(2);
                result += "\n";
            }
            binding.tvResult.setText(result);
        });

    }
}