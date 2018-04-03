package com.lwb.lajitianqi.MVP.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lwb.lajitianqi.Adapter.RunCityAdapter;
import com.lwb.lajitianqi.Base.BaseFragment;
import com.lwb.lajitianqi.Base.BasePresenter;
import com.lwb.lajitianqi.Bean.RunCityBeen;
import com.lwb.lajitianqi.Constant;
import com.lwb.lajitianqi.PublicFragmentActivity;
import com.lwb.lajitianqi.R;
import com.lwb.lajitianqi.Utils.FramentManages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwb on 2018/4/3.
 * 管理 城市
 */

public class RunCityFragment extends BaseFragment implements View.OnClickListener {
    /**
     * Frament退栈监听
     *
     * @param returnCode 返回码
     * @param bundle
     */
    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    /**
     * 中心的标题设置
     */
    @Override
    public void setMiddleTitle() {
        activity.toolbar.setTitle(R.string.run_city);
    }

    /**
     * 绑定P层
     *
     * @return
     */
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    /**
     * 绑定界面
     *
     * @return
     */
    @Override
    protected int bindLayout() {
        return R.layout.fragment_runcity;
    }
    List<RunCityBeen> datas=new ArrayList<>();
    /**
     * UI显示方法，必须在子类onCreateView方法内调用
     */
    @Override
    public void initView() {
        RecyclerView ct_recyclerView = (RecyclerView) fin(R.id.ct_recyclerView);
        LinearLayoutManager layout = new LinearLayoutManager(activity);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        ct_recyclerView.addItemDecoration(new DividerItemDecoration(activity,LinearLayoutManager.VERTICAL));
        ct_recyclerView.setLayoutManager(layout);
        datas.add(new RunCityBeen("漳浦","31","多云"));
        datas.add(new RunCityBeen("福州","28","晴"));
        RunCityAdapter runCityAdapter = new RunCityAdapter(activity,datas);
        ct_recyclerView.setAdapter(runCityAdapter);
    }

    /**
     * data数据方法，必须在子类onCreateView方法内调用
     */
    @Override
    public void initData() {

    }

    /**
     * listener事件监听方法，必须在子类onCreateView方法内调用
     */
    @Override
    public void initListener() {
        TextView tv_addcity = (TextView) fin(R.id.tv_addcity);
        tv_addcity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.Bundle_key,getString(R.string.add_city));
        switch (view.getId()){
            case R.id.tv_addcity:
                addFrament(R.id.fr_contain, FramentManages.AddCity,bundle,true);
                break;
        }
    }
}
