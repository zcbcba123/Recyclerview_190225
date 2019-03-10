package com.love.flower.recyclerview_190225;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView rv;
    private NormalAdapter normalAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv_main);
        List<String> data = initData();
//        rv.setLayoutManager(new LinearLayoutManager(this));
        layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        normalAdapter = new NormalAdapter(data, new NormalAdapter.OnItemClickListener() {
            @Override
            public void onClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, "点击了item", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, "长按了item", Toast.LENGTH_LONG).show();
            }
        });
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        rv.setItemAnimator(defaultItemAnimator);
        //禁用change动画
//        ((SimpleItemAnimator)rv.getItemAnimator()).setSupportsChangeAnimations(false);
        rv.setAdapter(normalAdapter);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println();
            }
        }));
    }

    public void add(View view){
        normalAdapter.addNewItem();
        layoutManager.scrollToPosition(0);

    }

    public void del(View view) {
    normalAdapter.deleteItem();
        layoutManager.scrollToPosition(0);
    }

    public void change(View view) {
        normalAdapter.changItem();
//        layoutManager.scrollToPosition(0);
    }

    private List<String> initData() {
       List list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        return list;
    }
}
