/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.lwb.lajitianqi.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * TODO 屏幕相关类
 */
public class ScreenUtil {
	private ScreenUtil(){}

	/**
	 * 1、获取屏幕宽度，单位为px
	 * @param context   应用程序上下文
	 * @return 屏幕宽度，单位px
	 */
	public static int getScreenWidth(Context context){
		return getDisplayMetrics(context).widthPixels;
	}

	/**
	 * 2、获取屏幕高度，单位为px
	 * @param context   应用程序上下文
	 * @return 屏幕高度，单位px
	 */
	public static int getScreenHeight(Context context){
		return getDisplayMetrics(context).heightPixels;
	}



	/**
	 * 获取DisplayMetrics对象
	 * @param context   应用程序上下文
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context){
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}
	// 状态栏高度
	private static  int statusBarHeight = 0;
	// 屏幕像素点
	private static final Point screenSize = new Point();

	// 获取屏幕像素点
	public static Point getScreenSize(Activity context) {

		if (context == null) {
			return screenSize;
		}
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		if (wm != null) {
			DisplayMetrics mDisplayMetrics = new DisplayMetrics();
			Display diplay = wm.getDefaultDisplay();
			if (diplay != null) {
				diplay.getMetrics(mDisplayMetrics);
				int W = mDisplayMetrics.widthPixels;
				int H = mDisplayMetrics.heightPixels;
				if (W * H > 0 && (W > screenSize.x || H > screenSize.y)) {
					screenSize.set(W, H);
				}
			}
		}
		return screenSize;
	}

}
