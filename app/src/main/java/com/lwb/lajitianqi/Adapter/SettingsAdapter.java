package com.lwb.lajitianqi.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.lwb.lajitianqi.BaseApplication;
import com.lwb.lajitianqi.Bean.SettingsBean;
import com.lwb.lajitianqi.Constant;
import com.lwb.lajitianqi.PublicFragmentActivity;
import com.lwb.lajitianqi.R;
import com.lwb.lajitianqi.Utils.SPUtils;

import java.util.List;

/**
 * Created by BlueSky on 2018/4/3.
 */

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingViewHolder>{

    private  List data;
    private  PublicFragmentActivity mActivity;

    public SettingsAdapter(PublicFragmentActivity activity, List datas) {
        this.data=datas;
        this.mActivity=activity;
    }

    @Override
    public SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.item_settings, null);
        return new SettingViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SettingViewHolder holder, int position) {
        Boolean Checked = (Boolean) SPUtils.get(mActivity, Constant.Sp_Switch, false);
        SettingsBean settings = (SettingsBean) data.get(position);
        int type = settings.getType();
        if(type==0){
            holder.switch1.setVisibility(View.VISIBLE);
            holder.tv_update.setVisibility(View.GONE);
        }else{
            holder.switch1.setVisibility(View.GONE);
            holder.tv_update.setVisibility(View.VISIBLE);
            holder.tv_update.setText("8小时");
        }
        holder.tv_director.setText(settings.getDirector());
        holder.switch1.setChecked(Checked);
        holder.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent intent = new Intent(Constant.Brodcast_Permiss);
                intent.putExtra(Constant.Brodcast,"Switch");
                if(b){
                    SPUtils.put(mActivity, Constant.Sp_Switch,true);
                    BaseApplication.getLocalBroadcast().sendBroadcast(intent);
                }else{
                    SPUtils.put(mActivity, Constant.Sp_Switch,false);
                    BaseApplication.getLocalBroadcast().sendBroadcast(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SettingViewHolder extends RecyclerView.ViewHolder{
        TextView tv_director;
        TextView tv_update;
        Switch switch1;
        public SettingViewHolder(View itemView) {
            super(itemView);
            tv_director= (TextView) itemView.findViewById(R.id.tv_director);
            tv_update= (TextView) itemView.findViewById(R.id.tv_update);
             switch1 = (Switch) itemView.findViewById(R.id.switch1);
        }
    }

}
