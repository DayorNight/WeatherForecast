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

package com.lwb.lajitianqi.Base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Toast;

import com.lwb.lajitianqi.R;
import com.lwb.lajitianqi.Utils.MyUtils;
import com.lwb.lajitianqi.Utils.OnFinishListener;
import com.lwb.lajitianqi.Utils.StringUtil;

import java.util.ArrayList;
import java.util.List;



//import android.view.GestureDetector;

/**
 * TODO 基础Activity，通过继承可获取或使用 里面创建的 组件 和 方法;不能用于FragmentActivity
 * @author Lemon
 * @use extends BaseActivity, 具体参考.DemoActivity
 * /滑动返回/显示与关闭进度弹窗方法/启动新Activity方法/快捷显示short toast方法/线程名列表/点击返回键事件/
 */
public abstract class BaseActivity extends Activity implements OnGestureListener, OnTouchListener {
	private static final String TAG = "BaseActivity";//用于打印日志（log）的类的标记

	/**
	 * TODO 在onCreate方法中赋值，不能在子Activity中创建
	 */
	protected BaseActivity context = null;
	/**
	 * TODO activity的主界面View，即contentView
	 */
	protected View view = null;
	/**
	 * TODO 该Activity是否已被使用并未被销毁。 在onCreate方法中赋值为true，不能在子Activity中创建
	 */
	protected boolean isAlive = false;
	/**
	 * TODO 添加该fragment是否在运行，不能在子Fragment中创建
	 */
	protected boolean isRunning = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		gestureDetector = new GestureDetector(this, this);//初始化手势监听类
		threadNameList = new ArrayList<String>();
	}

	//TODO 滑动返回<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	private OnFinishListener onFinishListener;
//	private GestureDetector gestureDetector;
	public void setContentView(int layoutResID, OnFinishListener listener) {
		super.setContentView(layoutResID);
		onFinishListener = listener;
		view = LayoutInflater.from(this).inflate(layoutResID, null);
		view.setOnTouchListener(this);
	};
	//TODO 滑动返回>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public static final String INTENT_TITLE = "INTENT_TITLE";
	public static final String INTENT_ID = "INTENT_ID";
	public static final String RESULT_DATA = "RESULT_DATA";

	/**
	 * TODO 可用于 打开activity以及activity之间的通讯（传值）等；一些通讯相关基本操作（打电话、发短信等）
	 */
	protected Intent intent = null;
	/**
	 * TODO 退出时之前的界面进入动画,可在finish();前通过改变它的值来改变动画效果
	 */
	protected int enterAnim = R.anim.fade;
	/**
	 * TODO 退出时该界面动画,可在finish();前通过改变它的值来改变动画效果
	 */
	protected int exitAnim = R.anim.right_push_out;

	/**
	 * TODO 进度弹窗
	 */
	protected ProgressDialog progressDialog = null;
	/**
	 * TODO activity退出时隐藏软键盘需要，需要在调用finish方法前赋值
	 */
	protected View toGetWindowTokenView = null;
	
	
	

	/**
	 * TODO UI显示方法，必须在子类onCreate方法内setContentView后调用
	 */
	public abstract void initView();
	/**
	 * TODO data数据方法，必须在子类onCreate方法内setContentView后调用
	 */
	public abstract void initData();
	/**
	 * TODO listener事件监听方法，必须在子类onCreate方法内setContentView后调用
	 */
	public abstract void initListener();


	//TODO 显示与关闭进度弹窗方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**TODO 展示加载进度条,无标题
	 * @param stringResId
	 */
	public void showProgressDialog(int stringResId){
		try {
			showProgressDialog(null, getString(stringResId));
		} catch (Exception e) {
			Log.e(TAG, "showProgressDialog  showProgressDialog(null, getString(stringResId));");
		}
	}
	/**TODO 展示加载进度条,无标题
	 * @param dialogMessage
	 */
	public void showProgressDialog(String dialogMessage){
		showProgressDialog(null, dialogMessage);
	}
	/**TODO 展示加载进度条
	 * @param dialogTitle 标题
	 * @param dialogMessage 信息
	 */
	public void showProgressDialog(final String dialogTitle, final String dialogMessage){
		if (isAlive == false) {
			return;
		}
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (progressDialog == null) {
					progressDialog = new ProgressDialog(context);
				}
				if(progressDialog.isShowing() == true) {
					progressDialog.dismiss();
				}
				if (dialogTitle != null && ! "".equals(dialogTitle.trim())) {
					progressDialog.setTitle(dialogTitle);
				}
				if (dialogMessage != null && ! "".equals(dialogMessage.trim())) {
					progressDialog.setMessage(dialogMessage);
				}
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.show();
			}
		});
	}


	/** TODO 隐藏加载进度
	 */
	public void dismissProgressDialog() {
		if(isAlive && progressDialog != null && progressDialog.isShowing() == true){

			runOnUiThread(new Runnable() {
				@Override
				public void run() {

					progressDialog.dismiss();
				}
			});
		}
	}
	//TODO 显示与关闭进度弹窗方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//TODO 启动新Activity方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**TODO 打开新的Activity，向左滑入效果
	 * @param intent
	 */
	public void toActivity(final Intent intent) {
		toActivity(intent, true);
	}
	/**TODO 打开新的Activity
	 * @param intent
	 * @param showAnimation
	 */
	public void toActivity(final Intent intent, final boolean showAnimation) {
		toActivity(intent, -1, showAnimation);
	}
	/**TODO 打开新的Activity，向左滑入效果
	 * @param intent
	 * @param requestCode
	 */
	public void toActivity(final Intent intent, final int requestCode) {
		toActivity(intent, requestCode, true);
	}
	/**TODO 打开新的Activity
	 * @param intent
	 * @param requestCode
	 * @param showAnimation
	 */
	public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
		if (isAlive == false || intent == null) {
			Log.e(TAG, "toActivity  isAlive == false || intent == null >> return;");
			return;
		}
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (requestCode < 0) {
					startActivity(intent);
				} else {
					startActivityForResult(intent, requestCode);
				}
				if (showAnimation) {
					overridePendingTransition(R.anim.right_push_in, R.anim.hold);
				} else {
					overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
				}
			}
		});
	}
	//TODO 启动新Activity方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//TODO show short toast 方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**TODO 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param stringResId
	 */
	public void showShortToast(int stringResId) {
		try {
			showShortToast(context.getResources().getString(stringResId));
		} catch (Exception e) {
			Log.e(TAG, "showShortToast  context.getResources().getString(resId) >>  catch (Exception e) {" + e.getMessage());
		}
	}
	/**TODO 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param string
	 */
	public void showShortToast(final String string) {
		showShortToast(string, false);
	}
	/**TODO 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param string
	 * @param isForceDismissProgressDialog
	 */
	public void showShortToast(final String string, final boolean isForceDismissProgressDialog) {
		if (isAlive == false || StringUtil.isNotEmpty(string, true) == false) {
			return;
		}
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (isForceDismissProgressDialog == true) {
					dismissProgressDialog();
				}

				Toast.makeText(context, "" + string, Toast.LENGTH_SHORT).show();
			}
		});
	}
	//TODO show short toast 方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	/**
	 * TODO 线程名列表
	 */
	protected List<String> threadNameList;


	@Override
	public void finish() {
		super.finish();//必须写在最前才能显示自定义动画
		//里面的代码不需要重写，通过super.finish();即可得到<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		if (isAlive == false) {
			return;
		}
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (toGetWindowTokenView != null) {
					MyUtils.hideKeyboard(context, toGetWindowTokenView);
				}
				if (enterAnim > 0 && exitAnim > 0) {
					try {
						overridePendingTransition(enterAnim, exitAnim);
					} catch (Exception e) {
						Log.e(TAG, "finish overridePendingTransition(enterAnim, exitAnim); >> catch (Exception e) {  " + e.getMessage());
					}
				}
			}
		});
		//里面的代码不需要重写，通过super.finish();即可得到>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	}


	@Override
	protected void onResume() {
		super.onResume();
		isRunning = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		isRunning = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//里面的代码不需要重写，通过super.onDestroy();即可得到<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		dismissProgressDialog();
		isRunning = false;
		isAlive = false;
		//里面的代码不需要重写，通过super.onDestroy();即可得到>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	}


	//TODO 点击返回键事件<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private boolean isOnKeyLongPress = false;
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		isOnKeyLongPress = true;
		return true;
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (isOnKeyLongPress) {
			isOnKeyLongPress = false;
			return true;
		}

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (onFinishListener != null) {
				onFinishListener.finish();
				return true;
			}
			break;
		default:
			break;
		}

		return super.onKeyUp(keyCode, event);
	}

	//TODO 点击返回键事件>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//TODO 滑动返回<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
	}


	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

		if (onFinishListener != null) {

			float maxDragHeight = getResources().getDimension(R.dimen.page_drag_max_height);
			float distanceY = e2.getRawY() - e1.getRawY();
			if (distanceY < maxDragHeight && distanceY > - maxDragHeight) {

				float minDragWidth = getResources().getDimension(R.dimen.page_drag_min_width);
				float distanceX = e2.getRawX() - e1.getRawX();
				if (distanceX > minDragWidth) {
					onFinishListener.finish();
					return true;
				}
			}
		}

		return false;
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
//		gestureDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
//		return gestureDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	//TODO 滑动返回>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}