package com.example.ch6.section1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest21Binding;
import com.example.ch6.databinding.ItemRecyclerBinding;

import java.util.ArrayList;

public class Test2_1Activity extends AppCompatActivity {
    ActivityTest21Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTest21Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("Item " + (i+1));
        }

        MyAdapter adapter = new MyAdapter(list);
        binding.main.setAdapter(adapter);
        binding.main.setLayoutManager(new LinearLayoutManager(this));
        binding.main.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    ItemRecyclerBinding binding;

    MyViewHolder(ItemRecyclerBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}

class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<String> data;

    MyAdapter(ArrayList<String> data) {
        this.data = data;
    }
    //항목을 구성하기 위해 뷰를 가지는 뷰홀더를 결정하기 위해 자동호출
    //이 함수에서 사용하고자 하는 뷰홀더를 생성해서 리턴

    //뷰홀더를 결정하기 위해서 이 함수가 자동호출
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerBinding binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MyViewHolder(binding);
    }

    //첫번째 매개변수  onCreateViewHolder에서 리턴시킨 뷰 홀더를 전달해서 이 뷰홀더에 작업
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.itemData.setText(data.get(position));
    }

    //항목갯수
    @Override
    public int getItemCount() {
        return data.size();
    }
}