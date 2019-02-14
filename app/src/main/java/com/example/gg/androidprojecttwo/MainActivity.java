package com.example.gg.androidprojecttwo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button icon_yj;
    private Button icon_yx;
    private Button icon_bf;
    private Button icon_jjs;
    private Button icon_hc;
    private Button icon_Gif;
    private SimpleDraweeView icon_sim;
    private Uri uri = Uri.parse("http://wx1.sinaimg.cn/wap720/0069vPp2ly1fhsuabugnsj30k00c0dh4.jpg");
    private RoundingParams params;
    private AbstractDraweeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        icon_yj = (Button) findViewById(R.id.icon_yj);
        icon_yx = (Button) findViewById(R.id.icon_yx);
        icon_bf = (Button) findViewById(R.id.icon_bf);
        icon_jjs = (Button) findViewById(R.id.icon_jjs);
        icon_hc = (Button) findViewById(R.id.icon_hc);
        icon_Gif = (Button) findViewById(R.id.icon_Gif);
        icon_sim = (SimpleDraweeView) findViewById(R.id.icon_sim);

        icon_yj.setOnClickListener(this);
        icon_yx.setOnClickListener(this);
        icon_bf.setOnClickListener(this);
        icon_jjs.setOnClickListener(this);
        icon_hc.setOnClickListener(this);
        icon_Gif.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_yj:
                params = RoundingParams.fromCornersRadii(30,30,30,30);
                icon_sim.getHierarchy().setRoundingParams(params);
                icon_sim.setImageURI(uri);

                break;
            case R.id.icon_yx:
                params = RoundingParams.asCircle();
                icon_sim.getHierarchy().setRoundingParams(params);
                icon_sim.setImageURI(uri);
                break;
            case R.id.icon_bf:
                break;
            case R.id.icon_jjs:
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                controller = Fresco.newDraweeControllerBuilder()
                        .setOldController(icon_sim.getController())
                        .setImageRequest(request)
                        .build();
                icon_sim.setController(controller);

                break;
            case R.id.icon_hc:

                break;
            case R.id.icon_Gif:
                Uri uri = Uri.parse("http://img.mp.itc.cn/upload/20161115/6163765431c44d538b37d6efb32ee885_th.jpg");
                controller = Fresco.newDraweeControllerBuilder()
                        .setOldController(icon_sim.getController())
                        .setUri(uri)
                        .setAutoPlayAnimations(true)
                        .setTapToRetryEnabled(true)
                        .build();
                icon_sim.setController(controller);
                break;
        }
    }
}
