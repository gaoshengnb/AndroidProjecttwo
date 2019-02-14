package com.example.weekone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weekone.adapter.SeekAdapter;
import com.example.weekone.bean.Seekbean;
import com.example.weekone.contacts.Contacts;
import com.example.weekone.presenter.PresenterImpl;
import com.example.weekone.utils.ImmersionUtil;
import com.example.weekone.view.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView, View.OnClickListener {

    @BindView(R.id.seek_edit)
    EditText seekEdit;
    @BindView(R.id.seek)
    ImageView seek;
    ArrayList<Seekbean.ResultBean> list = new ArrayList<>();
    @BindView(R.id.recy)
    RecyclerView recy;
    private SeekAdapter seekAdapter;
    private PresenterImpl presenter;
    private String s;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        seek.setOnClickListener(this);
//        // 隐藏标题栏
//        if (getSupportActionBar() != null){
//            getSupportActionBar().hide();
//        }
        //
        ImmersionUtil.fullScreen(this,false);
    }

    private void initData() {
        presenter = new PresenterImpl(this);
        final Map<String ,String > map=new HashMap<>();
        map.put("keyword",String.valueOf(s));
        map.put("page", String.valueOf(1));
        map.put("count", String.valueOf(10));
        presenter.startSeek(Contacts.Seek_Url,map);
        seekAdapter = new SeekAdapter(list, this);
        //视图管理
        manager = new GridLayoutManager(this,2);
        recy.setLayoutManager(manager);
    }

    @Override
    public void onClick(View v) {
        initData();
        s = seekEdit.getText().toString();

    }

    @Override
    public void Success(Object success) {
        list.clear();
        if (success instanceof Seekbean)
            list.addAll(((Seekbean) success).getResult());
        recy.setAdapter(seekAdapter);
        seekAdapter.notifyDataSetChanged();
    }
}
