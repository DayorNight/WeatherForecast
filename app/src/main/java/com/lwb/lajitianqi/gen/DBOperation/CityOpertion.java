package com.lwb.lajitianqi.gen.DBOperation;

import com.lwb.lajitianqi.BaseApplication;
import com.lwb.lajitianqi.Bean.CityManagerBean;
import com.lwb.lajitianqi.gen.CityManagerBeanDao;

import java.util.List;

/**
 * Created by lwb on 2018/4/8.
 */

public class CityOpertion {
    CityManagerBeanDao cityDao = BaseApplication.getInstances().getDaoSession().getCityManagerBeanDao();
    /**
     * 增
     * @param name
     */
    public void add(String name){
        cityDao.insert(new CityManagerBean(name));//添加一个
    }

    /**
     * 删
     * @param name
     */
    public void delete(String name){
        List<CityManagerBean> cityManagerBeen = selectAll();
        for (CityManagerBean Bean : cityManagerBeen){
            if(Bean.getCityName().contains(name)){
                cityDao.delete(Bean);
            }
        }
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<CityManagerBean> selectAll(){
        List<CityManagerBean> cityManagerBeen = cityDao.loadAll();
        return cityManagerBeen;
    }
}
