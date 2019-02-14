package com.example.oneday.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oneday.R;
import com.example.oneday.bean.User;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter{
    private final int ONE_ITEM=1;
    private final int TWO_ITEM=2;
    private ArrayList<User.DataBean> list;
    private Context context;
    private View view;

    public MyAdapter(ArrayList<User.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder=null;
        switch (i){
            case ONE_ITEM:
                view = View.inflate(viewGroup.getContext(), R.layout.left, null);
                holder=new OneHolder(view);
                break;
            case TWO_ITEM:
                view = View.inflate(viewGroup.getContext(), R.layout.right, null);
                holder=new TwoHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        User.DataBean dataBean = list.get(i);
        if (viewHolder instanceof OneHolder){
            ((OneHolder)viewHolder).textOne.setText(dataBean.getTitle());
            ((OneHolder) viewHolder).imageOne.setImageURI(dataBean.getThumbnail_pic_s());

        }else {
            ((TwoHolder)viewHolder).textTwo.setText(dataBean.getTitle());
            ((TwoHolder) viewHolder).imageTwo.setImageURI(dataBean.getThumbnail_pic_s02());
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return ONE_ITEM;
        }else {
            return TWO_ITEM;
        }
    }
    //第一个多条目
    class OneHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView imageOne;
        public TextView textOne;
        public OneHolder(@NonNull View itemView) {
            super(itemView);
            imageOne=itemView.findViewById(R.id.imageOne);
            textOne=itemView.findViewById(R.id.textOne);
        }
    }
    //第二个多条目
    class TwoHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView imageTwo;
        public TextView textTwo;
        public TwoHolder(@NonNull View itemView) {
            super(itemView);
            imageTwo=itemView.findViewById(R.id.imageTwo);
            textTwo=itemView.findViewById(R.id.textTwo);
        }
    }
}
