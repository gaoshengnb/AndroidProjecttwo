package com.example.weekone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weekone.DetailsActivity;
import com.example.weekone.MainActivity;
import com.example.weekone.R;
import com.example.weekone.bean.Seekbean;
import com.example.weekone.contacts.Contacts;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class SeekAdapter extends RecyclerView.Adapter<SeekAdapter.ViewHolder> {
    private ArrayList<Seekbean.ResultBean> list;
    private Context context;

    public SeekAdapter(ArrayList<Seekbean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SeekAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.seek_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SeekAdapter.ViewHolder viewHolder, final int i) {
        final Seekbean.ResultBean resultBean = list.get(i);
        viewHolder.title_tv.setText(resultBean.getCommodityName()+"");
        viewHolder.price_tv.setText("￥"+resultBean.getPrice());
        viewHolder.yishou_tv.setText("销量"+resultBean.getSaleNum());
        Glide.with(context).load(resultBean.getMasterPic()).into(viewHolder.img_sim);
        //recyclerView点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //evetbus粘性事件
                EventBus.getDefault().postSticky(resultBean);

                context.startActivity(new Intent(context,DetailsActivity.class));
                Toast.makeText(context, resultBean.getCommodityId()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_tv,price_tv,yishou_tv;
        ImageView img_sim;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv=itemView.findViewById(R.id.title_tv);
            price_tv=itemView.findViewById(R.id.price_tv);
            yishou_tv=itemView.findViewById(R.id.yishou_tv);
            img_sim=itemView.findViewById(R.id.img_sim);
        }
    }
}
