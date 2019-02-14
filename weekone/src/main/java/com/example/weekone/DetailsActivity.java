package com.example.weekone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weekone.bean.DetailsBean;
import com.example.weekone.bean.Seekbean;
import com.example.weekone.contacts.Contacts;
import com.example.weekone.presenter.PresenterImpl;
import com.example.weekone.view.IView;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements IView {

    @BindView(R.id.detalis_image)
    XBanner detalisImage;
    @BindView(R.id.detalis_price)
    TextView detalisPrice;
    @BindView(R.id.detalis_sale)
    TextView detalisSale;
    @BindView(R.id.detalis_name)
    TextView detalisName;
    @BindView(R.id.detalis_weighnt)
    TextView detalisWeighnt;
    @BindView(R.id.detalis_web)
    WebView detalisWeb;
    List<String> datas = new ArrayList<>();
    private DetailsBean.ResultBean result;
    private PresenterImpl presenter;
    private Seekbean.ResultBean resultBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        //eventbus注册
        EventBus.getDefault().register(this);
        initData();
    }

    private void initData() {
        presenter = new PresenterImpl(this);
        final Map<String ,String > map=new HashMap<>();
        map.put("commodityId",String.valueOf(resultBean.getCommodityId()));
        presenter.startDetails(Contacts.Details_Url, map);
    }

    //eventbus接受消息
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void details(Object object) {
        //接受传来的值
        resultBean = (Seekbean.ResultBean) object;
    }

    @Override
    public void Success(Object success) {

        if (success instanceof DetailsBean) {
            result = ((DetailsBean) success).getResult();
            String picture = result.getPicture();
            String[] split = picture.split("\\,");
            for (int i = 0; i < split.length; i++) {
                datas.add(split[i]);
            }
            //banner轮播
            if (!datas.isEmpty()) {
                detalisImage.setData(datas, null);
                detalisImage.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(DetailsActivity.this).load(datas.get(position)).into((ImageView) view);
                    }
                });
            }
        }
        detalisPrice.setText("￥" + result.getPicture());
        detalisName.setText(result.getCommodityName() + "");
        detalisSale.setText("以售出" + result.getSaleNum());
        WebSettings settings = detalisWeb.getSettings();
        settings.setJavaScriptEnabled(true);//支持JS
        String js = "<script type=\"text/javascript\">" +
                "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                "imgs[i].style.width = '100%';" +  // 宽度改为100%
                "imgs[i].style.height = 'auto';" +
                "}" +
                "</script>";
        detalisWeb.loadDataWithBaseURL(null, result.getDetails() + js, "text/html", "utf-8", null);
    }
}
