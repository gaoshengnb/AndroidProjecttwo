package com.example.weekone.model;

import com.example.weekone.callback.CallBack;

import java.util.Map;

public interface Model {
    void getData(String url , Map<String,String> map, CallBack callBack);
    void getDetails(String url , Map<String,String> map, CallBack callBack);
}
