package com.lwb.lajitianqi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.android.volley.VolleyError;
import com.lwb.lajitianqi.Utils.RequestData;
import com.lwb.lajitianqi.Utils.SPUtils;
import com.lwb.lajitianqi.Utils.VolleyInterface;

/**
 * 自动更新天气
 */
public class MyUpWheatherService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        intent.putExtra(Constant.Brodcast,"Service");
        intent.setAction(Constant.Brodcast_Permiss);
        BaseApplication.getLocalBroadcast().sendBroadcast(intent);
        AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour=8*60*60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, MyUpWheatherService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
