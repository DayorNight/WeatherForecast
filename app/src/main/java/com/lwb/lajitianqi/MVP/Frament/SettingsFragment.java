package com.lwb.lajitianqi.MVP.Frament;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lwb.lajitianqi.Adapter.SettingsAdapter;
import com.lwb.lajitianqi.Base.BaseFragment;
import com.lwb.lajitianqi.Base.BasePresenter;
import com.lwb.lajitianqi.Bean.SettingsBean;
import com.lwb.lajitianqi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwb on 2018/4/3.
 * 设置
 */

public class SettingsFragment extends BaseFragment {


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
        return R.layout.fragment_settings;
    }

    List datas=new ArrayList<>();
    /**
     * UI显示方法，必须在子类onCreateView方法内调用
     */
    @Override
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) fin(R.id.recyclerView);
        LinearLayoutManager layout = new LinearLayoutManager(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layout);
        datas.add(new SettingsBean(getString(R.string.auto_update),0));
        datas.add(new SettingsBean(getString(R.string.updata_interval),1));
        SettingsAdapter settingsAdapter = new SettingsAdapter(activity,datas);
        recyclerView.setAdapter(settingsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(activity,LinearLayoutManager.VERTICAL));

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

    }
}
