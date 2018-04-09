package com.lwb.lajitianqi.Bean;

/**
 * Created by lwb on 2018/4/3.
 */
public class CityBean {
    int id;
    String name;
    public CityBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
