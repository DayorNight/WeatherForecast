package com.lwb.lajitianqi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lwb.lajitianqi.Adapter.HourlyAdapter;
import com.lwb.lajitianqi.Bean.AirBean;
import com.lwb.lajitianqi.Bean.WeatherBean;
import com.lwb.lajitianqi.Utils.FramentManages;
import com.lwb.lajitianqi.Utils.RequestData;
import com.lwb.lajitianqi.Utils.VolleyInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private TextView tv_temperature,tv_cond_txt,tv_tmp_max_min,tv_qlty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initMenu();
        initData();

    }

    /**
     * 空气质量数据
     */
    private void initAir() {
        RequestData.requestAir(this, "厦门", new VolleyInterface() {
            @Override
            public void onSuccessString(String result) {
                Log.e("onSuccessString: ", result);
                AirBean air = new Gson().fromJson(result, AirBean.class);
                tv_qlty.setText("空气"+air.getHeWeather6().get(0).getAir_now_city().getQlty());
            }
            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    /**
     * 获取UI
     */
    private void initUI() {
        tv_temperature = (TextView) findViewById(R.id.tv_temperature);
        tv_cond_txt = (TextView) findViewById(R.id.tv_cond_txt);
        tv_tmp_max_min = (TextView) findViewById(R.id.tv_tmp_max_min);
        tv_qlty = (TextView) findViewById(R.id.tv_qlty);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        HourlyAdapter hourlyAdapter = new HourlyAdapter();
        recyclerView.setAdapter(hourlyAdapter);
    }

    /**
     * 获取数据
     */
    private void initData() {
        //常规天气数据集合
        initWeather();
        //空气质量数据
        initAir();
        //hourly小时预报
        initHourly();
    }

    /**
     * hourly小时预报
     */
    private void initHourly() {
        RequestData.requestHourlyWeather(this, "厦门", new VolleyInterface() {
            @Override
            public void onSuccessString(String result) {
                Log.e("===initHourly====",""+result);
            }
            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    /**
     * 常规天气数据集合
     */
    private void initWeather() {
        RequestData.requestWeather(this, "厦门", new VolleyInterface() {
            @Override
            public void onSuccessString(String result) {
                Log.e(TAG, "onSuccessString: "+ result );
                WeatherBean weather = new Gson().fromJson(result, WeatherBean.class);
                setUI(weather);
            }
            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    /**
     * 设置数据
     */
    private void setUI( WeatherBean weather) {
        WeatherBean.HeWeather6Bean heWeather6Bean = weather.getHeWeather6().get(0);
        tv_temperature.setText(heWeather6Bean.getNow().getTmp());
        tv_cond_txt.setText(heWeather6Bean.getNow().getCond_txt());
        tv_tmp_max_min.setText(heWeather6Bean.getDaily_forecast().get(0).getTmp_max()+"℃/"+heWeather6Bean.getDaily_forecast().get(0).getTmp_min()+"℃");
    }

    /**
     * toolbar
     */
    private void initMenu() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        ImageView img_settings = (ImageView) findViewById(R.id.img_settings);
        tv_title.setOnClickListener(this);
        img_settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()){
            case R.id.tv_title:
                bundle.putString(Constant.Bundle_key,getString(R.string.run_city));
                startActivity(PublicFragmentActivity.createIntent(this, FramentManages.Run_City, bundle));
                break;
            case R.id.img_settings:
                bundle.putString(Constant.Bundle_key,getString(R.string.settings));
                startActivity(PublicFragmentActivity.createIntent(this, FramentManages.Settings, bundle));
                break;
        }
    }
}
