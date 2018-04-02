package com.lwb.lajitianqi.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.lwb.lajitianqi.Base.BaseActivity;
import com.lwb.lajitianqi.MVP.IView.IWelcomeView;
import com.lwb.lajitianqi.MVP.Presenter.WelcomePresenter;
import com.lwb.lajitianqi.MainActivity;
import com.lwb.lajitianqi.R;
import com.lwb.lajitianqi.Utils.ImageLoaderUtil;


public class WelcomeActivity extends BaseActivity implements IWelcomeView {
    public static final String Tag = "WelcomeActivity";
    private ImageView img_welcome;
    private WelcomePresenter welcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        context = this;
        isAlive = true;
        initView();
        initData();
    }

    @Override
    public void initView() {
        img_welcome = (ImageView) findViewById(R.id.img_welcome);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f,1);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(context,MainActivity.class);
                WelcomeActivity.this.toActivity(intent);
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        img_welcome.setAnimation(alphaAnimation);
    }

    @Override
    public void initData() {
        welcomePresenter = new WelcomePresenter(this, this);
        welcomePresenter.getImage();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void showImage(String url) {
        ImageLoaderUtil.loadImage(img_welcome,url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        welcomePresenter.unBindP();
    }
}
