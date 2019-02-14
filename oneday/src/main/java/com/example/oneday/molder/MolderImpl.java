package com.example.oneday.molder;

import android.os.Handler;
import android.os.Message;

import com.example.oneday.bean.User;
import com.example.oneday.callback.CallBack;
import com.example.oneday.contacts.Contacts;
import com.example.oneday.service.Service;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MolderImpl implements molder{
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonstr = (String) msg.obj;
            User user= new Gson().fromJson(jsonstr, User.class);
            callBack.setData(user);
        }
    };
    private CallBack callBack;
    @Override
    public void getData(String url, CallBack callBack) {
        this.callBack=callBack;
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //接口头部
                .baseUrl(Contacts.Hend)
                .build();
        Service service = retrofit.create(Service.class);
        Call<ResponseBody> get = service.get(url);
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
}
