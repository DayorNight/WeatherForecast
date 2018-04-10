package com.lwb.lajitianqi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lwb.lajitianqi.Bean.CityManagerBean;
import com.lwb.lajitianqi.R;

import java.util.List;

/**
 * Created by lwb on 2018/4/3.
 */

public class RunCityAdapter extends RecyclerView.Adapter<RunCityAdapter.RunCityViewHolder>{

    private Activity mActivity;
    private   List<CityManagerBean> data;

    public RunCityAdapter(Activity activity, List<CityManagerBean> datas) {
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
        CityManagerBean runCityBeen = data.get(position);
        holder.tv_address.setText(runCityBeen.getCityName());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    class RunCityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tv_address;
        public RunCityViewHolder(View v) {
            super(v);
            tv_address= (TextView) v.findViewById(R.id.tv_address);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListen.onItemClick(view,getPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onItemClickListen.onLongItemClick(view,getPosition());
            return true;
        }
    }

    public RunCityAdapter.OnItemClickListen onItemClickListen;

    public interface OnItemClickListen{
        void onItemClick(View v,int posiontion);

        void onLongItemClick(View v,int posiontion);
    }

    public void setOnItemClickListen(RunCityAdapter.OnItemClickListen ItemClickListen) {
        onItemClickListen=ItemClickListen;
    }
}
