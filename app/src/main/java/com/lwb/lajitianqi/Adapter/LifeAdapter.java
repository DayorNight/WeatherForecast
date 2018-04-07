package com.lwb.lajitianqi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwb.lajitianqi.Bean.WeatherBean;
import com.lwb.lajitianqi.Bean.WeatherBean.HeWeather6Bean.LifestyleBean;
import com.lwb.lajitianqi.MainActivity;
import com.lwb.lajitianqi.R;

import java.util.List;

/**
 * Created by lwb on 2018/4/6.
 */

public class LifeAdapter extends RecyclerView.Adapter<LifeAdapter.LifeViewHolder>{

    private final Activity mainActivity;
    private final List<LifestyleBean> lifestyle;

    public LifeAdapter(Activity mainActivity, List<LifestyleBean> lifestyles) {

        this.mainActivity = mainActivity;
        this.lifestyle = lifestyles;
    }

    @Override
    public LifeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mainActivity).inflate(R.layout.item_life,null);
        return new LifeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(LifeViewHolder holder, int position) {
        LifestyleBean lifestyleBean = lifestyle.get(position);
        holder.tv_life_name.setText("舒适度指数");
        holder.tv_life_value.setText(lifestyleBean.getBrf());
        switch (lifestyleBean.getType()){
            case "comf":
                holder.tv_life_name.setText("舒适度指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "cw":
                holder.tv_life_name.setText("洗车指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "drsg":
                holder.tv_life_name.setText("穿衣指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "flu":
                holder.tv_life_name.setText("感冒指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "sport":
                holder.tv_life_name.setText("运动指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "trav":
                holder.tv_life_name.setText("旅游指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "uv":
                holder.tv_life_name.setText("紫外线指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "air":
                holder.tv_life_name.setText("空气污染指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "ac":
                holder.tv_life_name.setText("空调开启指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "ag":
                holder.tv_life_name.setText("过敏指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "ptfc":
                holder.tv_life_name.setText("交通指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "fisin":
                holder.tv_life_name.setText("钓鱼指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
            case "spi":
                holder.tv_life_name.setText("防晒指数");
                holder.tv_life_value.setText(lifestyleBean.getBrf());
                break;
        }

    }

    @Override
    public int getItemCount() {

        return lifestyle.size();
    }

    class LifeViewHolder extends RecyclerView.ViewHolder{
        TextView tv_life_name,tv_life_value;
        public LifeViewHolder(View v) {
            super(v);
            tv_life_name = (TextView) v.findViewById(R.id.tv_life_name);
            tv_life_value  = (TextView) v.findViewById(R.id.tv_life_value);
        }
    }
}
