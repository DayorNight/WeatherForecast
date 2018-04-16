package com.lwb.lajitianqi;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lwb.lajitianqi.Adapter.DailyForecastAdapter;
import com.lwb.lajitianqi.Adapter.LifeAdapter;
import com.lwb.lajitianqi.Bean.AirBean;
import com.lwb.lajitianqi.Bean.AirBean.HeWeather6Bean.AirNowCityBean;
import com.lwb.lajitianqi.Bean.WeatherBean;
import com.lwb.lajitianqi.Bean.WeatherBean.HeWeather6Bean.DailyForecastBean;
import com.lwb.lajitianqi.Bean.WeatherBean.HeWeather6Bean.LifestyleBean;
import com.lwb.lajitianqi.Bean.WeatherBean.HeWeather6Bean.NowBean;
import com.lwb.lajitianqi.Utils.FramentManages;
import com.lwb.lajitianqi.Utils.RequestData;
import com.lwb.lajitianqi.Utils.SPUtils;
import com.lwb.lajitianqi.Utils.VolleyInterface;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final int ACCESS_FINE_LOCATION = 1;//内存
    private static final int WRITE_EXTERNAL_STORAGE = 2;//内存
    private Toolbar toolbar;
    private TextView tv_temperature, tv_cond_txt, tv_tmp_max_min, tv_qlty;
    private List<DailyForecastBean> daily_forecast = new ArrayList<>();
    private DailyForecastAdapter hourlyAdapter;
    private TextView tv_pm10, tv_pm25, tv_no2, tv_so2, tv_o3, tv_co, tv_wind_dir, tv_wind_sc, tv_wind_spd;
    private TextView tv_hum, tv_pcpn, tv_vis;
    private List<LifestyleBean> lifes = new ArrayList<>();
    private LifeAdapter lifeAdapter;
    private TextView tv_title;
    private SmartRefreshLayout smartRefresh;
    private MyUpWheatherService myService;
    private MyBroadReceiver myBroadReceiver;
    private String adress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBrodcast();
        initUI();
        initMenu();
        initData();
        initUpdata();

    }

    /**
     * 注册广播
     */
    private void initBrodcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.permiss.AAA");
        myBroadReceiver = new MyBroadReceiver();
        BaseApplication.getLocalBroadcast().registerReceiver(myBroadReceiver, intentFilter);
    }

    /**
     * 更新服务
     */
    private void initUpdata() {
        Boolean Sp_Switch = (Boolean) SPUtils.get(this, Constant.Sp_Switch, false);
        Intent intent = new Intent(MainActivity.this, MyUpWheatherService.class);
        if (Sp_Switch) {
            startService(intent);
        } else {
            stopService(intent);
        }
    }

    /**
     * 空气质量数据
     */
    private void initAir(String name) {
        RequestData.requestAir(this, name, new VolleyInterface() {
            @Override
            public void onSuccessString(String result) {
                AirBean air = new Gson().fromJson(result, AirBean.class);
                tv_qlty.setText("空气" + air.getHeWeather6().get(0).getAir_now_city().getQlty());
                AirNowCityBean air_now_city = air.getHeWeather6().get(0).getAir_now_city();
                tv_pm10.setText(air_now_city.getPm10());
                tv_pm25.setText(air_now_city.getPm25());
                tv_no2.setText(air_now_city.getNo2());
                tv_so2.setText(air_now_city.getSo2());
                tv_co.setText(air_now_city.getCo());
                tv_o3.setText(air_now_city.getO3());
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
        smartRefresh = (SmartRefreshLayout) findViewById(R.id.smartRefresh);
        //空气质量
        tv_pm10 = F(R.id.tv_PM10);
        tv_pm25 = F(R.id.tv_PM25);
        tv_no2 = F(R.id.tv_NO2);
        tv_so2 = F(R.id.tv_SO2);
        tv_co = F(R.id.tv_CO);
        tv_o3 = F(R.id.tv_O3);
        //风向降水量
        tv_wind_dir = F(R.id.tv_wind_dir);
        tv_wind_sc = F(R.id.tv_wind_sc);
        tv_wind_spd = F(R.id.tv_wind_spd);
        tv_hum = F(R.id.tv_hum);
        tv_pcpn = F(R.id.tv_pcpn);
        tv_vis = F(R.id.tv_vis);
        //生活指数

        tv_temperature = F(R.id.tv_temperature);
        tv_cond_txt = F(R.id.tv_cond_txt);
        tv_tmp_max_min = F(R.id.tv_tmp_max_min);
        tv_qlty = F(R.id.tv_qlty);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        hourlyAdapter = new DailyForecastAdapter(this, daily_forecast);
        recyclerView.setAdapter(hourlyAdapter);

        RecyclerView rView = (RecyclerView) findViewById(R.id.rView);
        LinearLayoutManager Manager = new LinearLayoutManager(this);
        Manager.setOrientation(LinearLayoutManager.VERTICAL);
        rView.setLayoutManager(Manager);
        lifeAdapter = new LifeAdapter(this, lifes);
        rView.setAdapter(lifeAdapter);

        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                String cityName = (String) SPUtils.get(MainActivity.this, "CityName", "");
                if (!cityName.equals("")) {
                    initWeather(cityName);
                    //空气质量数据
                    initAir(cityName);
                } else if(adress!=null){
                    //常规天气数据集合
                    initWeather(adress);
                    //空气质量数据
                    initAir(adress);
                }
                smartRefresh.finishRefresh(2000);
            }
        });
    }

    public TextView F(int id) {
        TextView tv_cond_txt = (TextView) findViewById(id);
        return tv_cond_txt;
    }

    /**
     * 获取数据
     */
    private void initData() {
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},ACCESS_FINE_LOCATION);
            return;
        }else if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE);
            return;
        }else{
            //获取当前位置
                LocationManager systemService = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location location = systemService.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             if(location!=null){
                 double Longitude = location.getLongitude();//经度
                 double latitude = location.getLatitude();//纬度
                 String longitudes = String.valueOf(Longitude).substring(0,6);
                 String latitudes = String.valueOf(latitude).substring(0,6);
                 adress = longitudes + "," + latitudes;
                 //常规天气数据集合
                 initWeather(adress);
                 //空气质量数据
                 initAir(adress);
             }
        }
    }

    /**
     * 常规天气数据集合
     */
    private void initWeather(String name) {
        Log.e(TAG, "initWeather: "+name );
        RequestData.requestWeather(this, name, new VolleyInterface() {
            @Override
            public void onSuccessString(String result) {

                daily_forecast.clear();
                WeatherBean weather = new Gson().fromJson(result, WeatherBean.class);
                Log.e(TAG, "onSuccessString-------weather: "+weather );
                daily_forecast.addAll(weather.getHeWeather6().get(0).getDaily_forecast());
                hourlyAdapter.notifyDataSetChanged();
                List<LifestyleBean> lifestyle = weather.getHeWeather6().get(0).getLifestyle();
                if(lifestyle!=null){
                    lifes.clear();
                    lifes.addAll(lifestyle);
                    lifeAdapter.notifyDataSetChanged();
                }
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
        NowBean now = heWeather6Bean.getNow();
        tv_wind_dir.setText(now.getWind_dir());
        tv_wind_sc.setText(now.getWind_sc());
        tv_wind_spd.setText(now.getWind_spd());
        tv_hum.setText(now.getHum());
        tv_pcpn.setText(now.getPcpn());
        tv_vis.setText(now.getVis());
    }

    /**
     * toolbar
     */
    private void initMenu() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        tv_title = (TextView) findViewById(R.id.tv_title);
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
                startActivityForResult(PublicFragmentActivity.createIntent(this, FramentManages.Run_City, bundle),RESULT_CANCELED);
                break;
            case R.id.img_settings:
                bundle.putString(Constant.Bundle_key,getString(R.string.settings));
                startActivity(PublicFragmentActivity.createIntent(this, FramentManages.Settings, bundle));
                break;
        }
    }

    /**
     * Dispatch incoming result to the correct fragment.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                String cityName = data.getExtras().getString(Constant.ResultCode_city);
                Log.e(TAG, "onActivityResult: "+cityName);
                tv_title.setText(cityName);
                initWeather(cityName);
                initAir(cityName);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "onRequestPermissionsResult: "+111111 );
                initData();
            } else {
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE);
                }
            }
        }else if(requestCode == WRITE_EXTERNAL_STORAGE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "onRequestPermissionsResult: "+3333 );
                initData();
            } else {
                Log.e(TAG, "onRequestPermissionsResult: "+4444 );
                Toast.makeText(this, "权限已被禁止", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getLocalBroadcast().unregisterReceiver(myBroadReceiver);
    }

    class MyBroadReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String string = intent.getExtras().getString(Constant.Brodcast);
            switch (string){
                case "Service":
                    initData();
                    break;
                case "Switch":
                    initUpdata();
                    break;
            }

        }
    }
}
