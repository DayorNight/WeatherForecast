package com.lwb.lajitianqi.MVP.Presenter;


import com.lwb.lajitianqi.Base.BasePresenter;
import com.lwb.lajitianqi.MVP.IView.IWelcomeView;
import com.lwb.lajitianqi.MVP.WelcomeActivity;

/**
 * Created by Administrator on 2017/9/6.
 */

public class WelcomePresenter extends BasePresenter {

    private WelcomeActivity context;
    private IWelcomeView iWelcomeView;

    public WelcomePresenter(IWelcomeView iWelcomeView, WelcomeActivity context) {
        this.iWelcomeView=iWelcomeView;
        this.context=context;
    }

    /**
     * 请求首页图片
     * https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1986366172,522581785&fm=27&gp=0.jpg
     */
    public void getImage(){
        String url="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1986366172,522581785&fm=27&gp=0.jpg";
        iWelcomeView.showImage(url);
    }

    @Override
    public void unBindP() {
        context=null;
        iWelcomeView=null;
    }
}
