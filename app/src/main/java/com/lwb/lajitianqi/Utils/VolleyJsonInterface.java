package com.lwb.lajitianqi.Utils;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import static com.android.volley.Response.ErrorListener;
import static com.android.volley.Response.Listener;

/**
 * Created by Administrator on 2017/9/6.
 */

public abstract class VolleyJsonInterface {

    public static Listener mListener;
    public static ErrorListener mErrorListener;

    public VolleyJsonInterface() {}

    public abstract void onSuccessJSONObject(JSONObject result);//成功的回调

    public abstract void onError(VolleyError error);//失败的回调

    public Listener successListener() {
        mListener = new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onSuccessJSONObject(response);
            }
        };
        return mListener;
    }

    public ErrorListener errorListener() {
        mErrorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//提示请求失败的对话框，避免重复书写
                onError(error);
            }
        };
        return mErrorListener;
    }
}
