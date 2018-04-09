package com.lwb.lajitianqi;

import com.lwb.lajitianqi.Utils.VolleyUtil;

/**
 * Created by WESTAKE on 2017/5/17.
 */

public class Constant {
    //和风天气
    public final static String Key="d13ceb2a5c4544598c87716d70471922";
    public final static String ID="HE1804041548451780";
    //城市接口
    public final static String AddressUrl="http://guolin.tech/api/china";
    //天气预报
    public final static String forecastUrl="https://free-api.heweather.com/s6/weather/forecast";
    //实况天气 实况天气每小时至少更新两次
    public final static String NowWeatherUrl="https://free-api.heweather.com/s6/weather/now";
    //逐小时预报
    public final static String HourlyWeatherUrl="https://free-api.heweather.com/s6/weather/hourly";
    //生活指数
    public final static String LifestyleUrl="https://free-api.heweather.com/s6/weather/lifestyle";
    //常规天气数据集合
    public final static String WeatherUrl="https://free-api.heweather.com/s6/weather";
    //空气质量数据集合
    public final static String AirUrl="https://free-api.heweather.com/s6/air";
    //城市查询
    public final static String SearchUrl="https://free-api.heweather.com/s6/search";

    /**
     *  intent
     */
    public final static String Intent_Go="跳转到哪个片段";
    public final static String Bundle_key="Bundle_key";
    public final static String ResultCode_city="ResultCode_city";

    public final static String Sp_Switch="Sp_Switch";
    public final static String Sp_WeatherResult="Sp_WeatherResult";
    public final static String Brodcast="Brodcast";
    public final static String Brodcast_Permiss="android.permiss.AAA";

}
