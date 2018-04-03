package com.lwb.lajitianqi.Bean;

/**
 * Created by BlueSky on 2018/4/3.
 */

public class RunCityBeen {
    String address;
    String  temperature;
    String   weather;

    public RunCityBeen(String address, String temperature, String weather) {
        this.address = address;
        this.temperature = temperature;
        this.weather = weather;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
