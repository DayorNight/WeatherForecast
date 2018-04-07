package com.lwb.lajitianqi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwb.lajitianqi.Bean.WeatherBean.HeWeather6Bean.DailyForecastBean;
import com.lwb.lajitianqi.R;

import java.util.List;

/**
 * Created by lwb on 2018/4/4.
 */

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.HourlyViewHolder>{

    private final Activity mainActivity;
    private final List<DailyForecastBean> daily_forecast;

    public DailyForecastAdapter(Activity mainActivity, List<DailyForecastBean> daily_forecast) {
        this.mainActivity = mainActivity;
        this.daily_forecast = daily_forecast;
    }

    @Override
    public HourlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.item_daily_forecast,parent,false);
        return new HourlyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(HourlyViewHolder holder, int position) {
        DailyForecastBean dailyForecastBean = daily_forecast.get(position);
        holder.tv_item_date.setText(dailyForecastBean.getDate());
        holder.tv_item_cond_txt_d.setText(dailyForecastBean.getCond_txt_d());
        holder.tv_item_tmp.setText(dailyForecastBean.getTmp_max()+"℃/"+dailyForecastBean.getTmp_min()+"℃");
    }

    @Override
    public int getItemCount() {
        return daily_forecast.size();
    }

    class HourlyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_item_date,tv_item_cond_txt_d,tv_item_tmp;
        public HourlyViewHolder(View v) {
            super(v);
            tv_item_date = (TextView) v.findViewById(R.id.tv_item_date);
            tv_item_cond_txt_d  = (TextView) v.findViewById(R.id.tv_item_cond_txt_d);
            tv_item_tmp  = (TextView) v.findViewById(R.id.tv_item_tmp);
        }
    }
}
