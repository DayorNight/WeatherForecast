package com.lwb.lajitianqi.Utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by lwb on 2018/4/2.
 */

public class MyUtils {
    private static final String TAG = "MyUtils";
    private static InputMethodManager imm;
    /**1隐藏输入法
     * @param context
     * @param toGetWindowTokenView
     */
    public static void hideKeyboard(Context context, View toGetWindowTokenView){
        showKeyboard(context, null, toGetWindowTokenView, false);
    }
    /**5显示/隐藏输入框
     * @param context
     * @param et
     * @param toGetWindowTokenView(为null时toGetWindowTokenView = et) 包含et的父View，键盘根据toGetWindowTokenView的位置来弹出/隐藏
     * @param show
     */
    public static void showKeyboard(Context context, EditText et, View toGetWindowTokenView, boolean show){
        if (context == null) {
            Log.e(TAG, "showKeyboard  context == null >> return;");
            return;
        }

        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);//imm必须与context唯一对应
        if (toGetWindowTokenView == null) {
            Log.w(TAG, "showKeyboard   toGetWindowTokenView == null");
            toGetWindowTokenView = et;
        }
        if (toGetWindowTokenView == null) {
            Log.e(TAG, "showKeyboard  toGetWindowTokenView == null && et == null  >> return;");
            return;
        }

        if (show == false) {
            imm.hideSoftInputFromWindow(toGetWindowTokenView.getWindowToken(), 0);
            if (et != null) {
                et.clearFocus();
            }
        } else {
            if (et != null) {
                et.setFocusable(true);
                et.setFocusableInTouchMode(true);
                et.requestFocus();
                imm.toggleSoftInputFromWindow(toGetWindowTokenView.getWindowToken()
                        , InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }
    }
    /**
     * get请求拼接
     * @param url
     * @param params
     * @return
     */
    public static String urlParams(String url, LinkedHashMap<String,String> params){

        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");

        for (int i=0;i<params.size();i++ ) {
            String value=null;
            try {
                value= URLEncoder.encode(values.next(),"utf-8");
            }catch (Exception e){
                e.printStackTrace();
            }

            stringBuffer.append(keys.next()+"="+value);
            if (i!=params.size()-1) {
                stringBuffer.append("&");
            }
        }
        return url + stringBuffer.toString();
    }
}
