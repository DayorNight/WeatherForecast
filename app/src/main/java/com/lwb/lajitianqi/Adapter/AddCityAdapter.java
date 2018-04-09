package com.lwb.lajitianqi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwb.lajitianqi.Bean.CityBean;
import com.lwb.lajitianqi.R;

import java.util.List;

/**
 * Created by lwb on 2018/4/3.
 */

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.AddCityViewHolder> {
    private Activity mActivity;
    private List<CityBean> data;

    public AddCityAdapter(Activity activity, List<CityBean> datas) {
        this.mActivity=activity;
        this.data=datas;
    }

    @Override
    public AddCityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.item_addcity, null);
        return new AddCityViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(AddCityViewHolder holder, int position) {
        CityBean cityBean = data.get(position);
        holder.tv_city_name.setText(cityBean.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class AddCityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_city_name;
        public AddCityViewHolder(View v) {
            super(v);
            tv_city_name = (TextView) v.findViewById(R.id.tv_city_name);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListen.onItemClick(view,getPosition());
        }
    }

    public OnItemClickListen onItemClickListen;

    public interface OnItemClickListen{
       void onItemClick(View v,int posiontion);
    }

    public void setOnItemClickListen(OnItemClickListen ItemClickListen) {
        onItemClickListen=ItemClickListen;
    }

}
