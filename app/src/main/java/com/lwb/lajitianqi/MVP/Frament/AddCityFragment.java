package com.lwb.lajitianqi.MVP.Frament;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lwb.lajitianqi.Adapter.AddCityAdapter;
import com.lwb.lajitianqi.Adapter.AddCityAdapter.OnItemClickListen;
import com.lwb.lajitianqi.Base.BaseFragment;
import com.lwb.lajitianqi.Base.BasePresenter;
import com.lwb.lajitianqi.Bean.CityBean;
import com.lwb.lajitianqi.Bean.WeatherBean;
import com.lwb.lajitianqi.Constant;
import com.lwb.lajitianqi.R;
import com.lwb.lajitianqi.Utils.GridDivider;
import com.lwb.lajitianqi.Utils.RequestData;
import com.lwb.lajitianqi.Utils.VolleyInterface;
import com.lwb.lajitianqi.Utils.VolleyUtil;
import com.lwb.lajitianqi.gen.DBOperation.CityOpertion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwb on 2018/4/3.
 * 添加城市
 */

public class AddCityFragment extends BaseFragment {

    private RecyclerView add_recyclerView;
    private AddCityAdapter addCityAdapter;
    private String addressUrl;
    private TextView tv_cityName;
    private StringBuilder sb;
    private CityOpertion cityOpertion;

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
        return R.layout.fragment_addcity;
    }

    List<CityBean> datas=new ArrayList<>();
    /**
     * UI显示方法，必须在子类onCreateView方法内调用
     */
    @Override
    public void initView() {
        cityOpertion = new CityOpertion();
        add_recyclerView = (RecyclerView) fin(R.id.add_recyclerView);
        GridLayoutManager Manager = new GridLayoutManager(activity,3);
        add_recyclerView.setLayoutManager(Manager);
        addCityAdapter = new AddCityAdapter(activity,datas);
        add_recyclerView.setAdapter(addCityAdapter);
        add_recyclerView.addItemDecoration(new GridDivider(activity,2, Color.WHITE));
        tv_cityName = (TextView) fin(R.id.tv_cityName);
    }

    /**
     * data数据方法，必须在子类onCreateView方法内调用
     */
    @Override
    public void initData() {
        sb = new StringBuilder();
        getCity(Constant.AddressUrl,Constant.AddressUrl);
        addressUrl = Constant.AddressUrl;
    }
    int i=0;
    private void getCity(String url,String tag) {
        ++i;
        datas.clear();
        VolleyUtil.getString(activity,url,tag, new VolleyInterface() {
            @Override
            public void onSuccessString(String result) {
                List<CityBean> data= new Gson().fromJson(result, new TypeToken<List<CityBean>>() {
                }.getType());
                datas.addAll(data);
                addCityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onError(VolleyError error) {
            }
        });
    }

    /**
     * listener事件监听方法，必须在子类onCreateView方法内调用
     */

    @Override
    public void initListener() {
        addCityAdapter.setOnItemClickListen(new OnItemClickListen() {
            @Override
            public void onItemClick(View v, int posiontion) {
                CityBean cityBean = datas.get(posiontion);
                int id = cityBean.getId();
                String name = cityBean.getName();
                if(i>2){
                    cityOpertion.add(name);
                    popback(1,null);
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(addressUrl);
                stringBuilder.append("/"+id);
                addressUrl = stringBuilder.toString();
                getCity(addressUrl,addressUrl);
                setSelectCity(name);
            }
        });
    }


    private void setSelectCity(String name) {
        sb.append(name+" ");
        tv_cityName.setText(sb);
    }
}
