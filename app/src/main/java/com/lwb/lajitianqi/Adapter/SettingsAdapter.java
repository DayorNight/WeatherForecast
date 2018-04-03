package com.lwb.lajitianqi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lwb.lajitianqi.Bean.SettingsBean;
import com.lwb.lajitianqi.PublicFragmentActivity;
import com.lwb.lajitianqi.R;

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
        SettingsBean settings = (SettingsBean) data.get(position);
        holder.tv_director.setText(settings.getDirector());
        holder.tv_update.setText("1小时");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SettingViewHolder extends RecyclerView.ViewHolder{
        TextView tv_director;
        TextView tv_update;
        public SettingViewHolder(View itemView) {
            super(itemView);
            tv_director= (TextView) itemView.findViewById(R.id.tv_director);
            tv_update= (TextView) itemView.findViewById(R.id.tv_update);
        }
    }

}
