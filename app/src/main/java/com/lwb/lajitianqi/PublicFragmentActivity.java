package com.lwb.lajitianqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.lwb.lajitianqi.Base.BaseFragmentActivity;
import com.lwb.lajitianqi.Base.BasePresenter;


public class PublicFragmentActivity extends BaseFragmentActivity {

    public Toolbar toolbar;

    /**启动这个Activity的Intent 带参数
     * @param context
     * @return
     */

    public static Intent createIntent(Context context, String arg,Bundle bundle) {
        return new Intent(context, PublicFragmentActivity.class).putExtra(Constant.Intent_Go, arg).putExtras(bundle);
    }
    @Override
    protected int bindLayout() {
        return R.layout.activity_public_fragment;
    }

    /**
     * listener事件监听方法，必须在子类onCreate方法内setContentView后调用
     */
    @Override
    protected void initListener() {

    }


    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initUI() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        toolbar = (Toolbar) findViewById(R.id.toolbar_title);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回键
                // 如果剩下 最后一个片段就销毁Activity,否则执行后退栈
                if (fragmentManager.getAllFrament().size() <= 1)
                {
                    finish();
                } else
                {
                    fragmentManager.popBackStack();

                }
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String towhere = intent.getStringExtra(Constant.Intent_Go);
        if (towhere!=null) {
                Bundle bundle = intent.getExtras();
                fragmentManager.addFrament(R.id.fr_contain,this,towhere,bundle,true);
            }
        else
        {
            this.finish();
        }
    }
}
