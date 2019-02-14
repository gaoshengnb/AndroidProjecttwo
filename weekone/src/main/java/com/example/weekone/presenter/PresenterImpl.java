package com.example.weekone.presenter;

import com.example.weekone.callback.CallBack;
import com.example.weekone.model.ModelImpl;
import com.example.weekone.view.IView;

import java.util.Map;

public class PresenterImpl implements IPresenter {
    private ModelImpl model;
    private IView iView;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        model=new ModelImpl();
    }


    @Override
    public void startSeek(String url, Map<String, String> map) {
        model.getData(url, map, new CallBack() {
            @Override
            public void Success(Object success) {
                iView.Success(success);
            }
        });
    }

    @Override
    public void startDetails(String url, Map<String, String> map) {
        model.getDetails(url, map, new CallBack() {
            @Override
            public void Success(Object success) {
                iView.Success(success);
            }
        });
    }
}
