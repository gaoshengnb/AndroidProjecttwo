package com.example.oneday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.oneday.adapter.MyAdapter;
import com.example.oneday.bean.User;
import com.example.oneday.contacts.Contacts;
import com.example.oneday.presenter.PresenterImpl;
import com.example.oneday.view.IView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IView {

    private RecyclerView recy;
    private PresenterImpl presenter;
    private ArrayList<User.DataBean> list=new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        presenter = new PresenterImpl(this);
        presenter.strarReques(Contacts.Wei);
        adapter = new MyAdapter(list,this);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(adapter);
    }

    private void initView() {
        recy = (RecyclerView) findViewById(R.id.recy);
    }

    @Override
    public void success(User success) {
        User user=success;
        list.addAll(user.getData());
        adapter.notifyDataSetChanged();
    }
}
