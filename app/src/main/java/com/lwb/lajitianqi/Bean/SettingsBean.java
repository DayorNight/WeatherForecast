package com.lwb.lajitianqi.Bean;

/**
 * Created by BlueSky on 2018/4/3.
 */

public class SettingsBean {
    public SettingsBean(String director, int type) {
        this.director = director;
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    String director;
    int type;
}
