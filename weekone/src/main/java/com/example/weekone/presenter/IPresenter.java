package com.example.weekone.presenter;

import java.util.Map;

public interface IPresenter {
    void startSeek(String url, Map<String ,String > map);
    void startDetails(String url, Map<String ,String > map);
}
