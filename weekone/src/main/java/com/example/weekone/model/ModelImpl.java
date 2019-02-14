package com.example.weekone.model;

import android.os.Handler;
import android.os.Message;

import com.example.weekone.bean.DetailsBean;
import com.example.weekone.bean.Seekbean;
import com.example.weekone.callback.CallBack;
import com.example.weekone.contacts.Contacts;
import com.example.weekone.service.Service;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelImpl implements Model {
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String str= (String) msg.obj;
                    Gson gson=new Gson();
                    Seekbean seekbean=gson.fromJson(str,Seekbean.class);
                    callBack.Success(seekbean);
                    break;
                case 1:
                    String dstr= (String) msg.obj;
                    Gson dgson=new Gson();
                    DetailsBean detailsBean=dgson.fromJson(dstr,DetailsBean.class);
                    callBack.Success(detailsBean);
                    break;
            }
        }
    };
    private CallBack callBack;
    @Override
    public void getData(String url, Map<String, String> map, CallBack callBack) {
        this.callBack=callBack;
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //接口头部
                .baseUrl(Contacts.Hand_Url)
                .build();
        Service service = retrofit.create(Service.class);
        Call<ResponseBody> get = service.Seekget(url,map);
        get.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    handler.sendMessage(handler.obtainMessage(0,s));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void getDetails(String url, Map<String, String> map, CallBack callBack) {
        this.callBack=callBack;
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //接口头部
                .baseUrl(Contacts.Hand_Url)
                .build();
        Service service = retrofit.create(Service.class);
        Call<ResponseBody> get = service.Seekget(url,map);
        get.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    handler.sendMessage(handler.obtainMessage(1,s));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
