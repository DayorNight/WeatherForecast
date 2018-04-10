package com.lwb.lajitianqi.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lwb on 2018/4/8.
 */
@Entity
public class CityManagerBean {
    @Id(autoincrement=true)
    Long id;
    @Property(nameInDb  = "cityName")
    String cityName;
    @Generated(hash = 728106083)
    public CityManagerBean(Long id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }
    @Generated(hash = 916439494)
    public CityManagerBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
  
   
}
