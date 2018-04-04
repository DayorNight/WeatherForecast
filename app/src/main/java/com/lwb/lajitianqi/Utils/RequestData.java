package com.lwb.lajitianqi.Utils;

import android.content.Context;
import android.util.Log;

import com.lwb.lajitianqi.Constant;

import java.util.LinkedHashMap;

/**
 * Created by lwb on 2018/4/4.
 */

public class RequestData {
    /**
     * https://www.heweather.com/douments/api/s6/weather-forecast
     * 天气预报 普通用户仅获得 3天预报数据
     * location 城市名
     * key 用户认证key
     * lang 多语言 默认为简体中文
     * unit 单位选择 公制（m）或英制（i） 默认为公制单位
     */
    public static void requestForecast(Context context,String location, VolleyInterface vif){
        LinkedHashMap<String,String> params=new LinkedHashMap<>();
        params.put("location",location);
        params.put("key",Constant.Key);
//        params.put("lang","cn");//可选
//        params.put("unit","m");//可选
        VolleyUtil.getString(context, MyUtils.urlParams(Constant.forecastUrl,params),Constant.forecastUrl,vif);
    }

    /**
     * 实况天气
     * 当前时间点的天气状况以及温湿风压等气象指数，具体包含的数据：体感温度、实测温度、天气状况、风力、风速、风向、相对湿度、大气压强、降水量、能见度等。
     * @param context
     * @param location
     * @param vif
     */
    public static void requestNowForecast(Context context,String location, VolleyInterface vif){
        LinkedHashMap<String,String> params=new LinkedHashMap<>();
        params.put("location",location);
        params.put("key",Constant.Key);
//        params.put("lang","cn");//可选
//        params.put("unit","m");//可选
        VolleyUtil.getString(context, MyUtils.urlParams(Constant.NowWeatherUrl,params),Constant.NowWeatherUrl,vif);
    }

    /**
     * 逐小时预报
     * @param context
     * @param location
     * @param vif
     */
    public static void requestHourlyWeather(Context context,String location, VolleyInterface vif){
        LinkedHashMap<String,String> params=new LinkedHashMap<>();
        params.put("location",location);
        params.put("key",Constant.Key);
//        params.put("lang","cn");//可选
//        params.put("unit","m");//可选
        VolleyUtil.getString(context, MyUtils.urlParams(Constant.HourlyWeatherUrl,params),Constant.HourlyWeatherUrl,vif);
    }
    /**
     * 生活指数
     * @param context
     * @param location
     * @param vif
     */
    public static void requestLifestyWeather(Context context,String location, VolleyInterface vif){
        LinkedHashMap<String,String> params=new LinkedHashMap<>();
        params.put("location",location);
        params.put("key",Constant.Key);
//        params.put("lang","cn");//可选
//        params.put("unit","m");//可选
        VolleyUtil.getString(context, MyUtils.urlParams(Constant.LifestyleUrl,params),Constant.LifestyleUrl,vif);
    }
    /**
     * 常规天气数据集合
     * @param context
     * @param location
     * @param vif
     */
    public static void requestWeather(Context context,String location, VolleyInterface vif){
        LinkedHashMap<String,String> params=new LinkedHashMap<>();
        params.put("location",location);
        params.put("key",Constant.Key);
//        params.put("lang","cn");//可选
//        params.put("unit","m");//可选
        String url = MyUtils.urlParams(Constant.WeatherUrl, params);
        VolleyUtil.getString(context,url ,Constant.WeatherUrl,vif);
    }
    /**
     * 空气质量
     * @param context
     * @param location
     * @param vif
     */
    public static void requestAir(Context context,String location, VolleyInterface vif){
        LinkedHashMap<String,String> params=new LinkedHashMap<>();
        params.put("location",location);
        params.put("key",Constant.Key);
//        params.put("lang","cn");//可选
//        params.put("unit","m");//可选
        VolleyUtil.getString(context, MyUtils.urlParams(Constant.AirUrl,params),Constant.AirUrl,vif);
    }
    /**
     * 城市查询
     * @param context
     * @param location
     * @param vif
     */
    public static void requestSearch(Context context,String location, VolleyInterface vif){
        LinkedHashMap<String,String> params=new LinkedHashMap<>();
        params.put("location",location);
        params.put("key",Constant.Key);
//        params.put("lang","cn");//可选
        VolleyUtil.getString(context, MyUtils.urlParams(Constant.SearchUrl,params),Constant.SearchUrl,vif);
    }


}
