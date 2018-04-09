package com.lwb.lajitianqi.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lwb on 2018/4/8.
 */
@Entity
public class CityManagerBean {
    @Property(nameInDb  = "cityName")
    String cityName;

    @Generated(hash = 1142741904)
    public CityManagerBean(String cityName) {
        this.cityName = cityName;
    }

    @Generated(hash = 916439494)
    public CityManagerBean() {
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
