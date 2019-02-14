package com.example.oneday.presenter;

import com.example.oneday.bean.User;
import com.example.oneday.callback.CallBack;
import com.example.oneday.molder.MolderImpl;
import com.example.oneday.view.IView;

public class PresenterImpl implements IPresenter {
    private IView view;
    private MolderImpl molder;

    public PresenterImpl(IView view) {
        this.view = view;
        molder=new MolderImpl();
    }

    @Override
    public void strarReques(String url) {
        molder.getData(url, new CallBack() {
            @Override
            public void setData(User data) {
                view.success(data);
            }
        });
    }
}
