package com.lwb.lajitianqi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lwb.lajitianqi.Bean.RunCityBeen;
import com.lwb.lajitianqi.PublicFragmentActivity;
import com.lwb.lajitianqi.R;

import java.util.List;

/**
 * Created by lwb on 2018/4/3.
 */

public class RunCityAdapter extends RecyclerView.Adapter<RunCityAdapter.RunCityViewHolder>{

    private Activity mActivity;
    private   List<RunCityBeen> data;

    public RunCityAdapter(Activity activity, List<RunCityBeen> datas) {
        this.data=datas;
        this.mActivity=activity;
    }

    @Override
    public RunCityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.item_runcity, null);
        return new RunCityViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RunCityViewHolder holder, int position) {
        RunCityBeen runCityBeen = data.get(position);
        holder.tv_address.setText(runCityBeen.getAddress());
        holder.tv_temperature.setText(runCityBeen.getTemperature());
        holder.tv_weather.setText(runCityBeen.getWeather());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class RunCityViewHolder extends RecyclerView.ViewHolder{
        TextView tv_address;
        TextView tv_temperature;
        TextView tv_weather;
        public RunCityViewHolder(View v) {
            super(v);
            tv_address= (TextView) v.findViewById(R.id.tv_address);
              tv_temperature = (TextView) v.findViewById(R.id.tv_temperature);
              tv_weather = (TextView) v.findViewById(R.id.tv_weather);
        }
    }
}
