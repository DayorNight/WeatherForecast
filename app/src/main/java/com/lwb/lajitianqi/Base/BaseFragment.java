package com.lwb.lajitianqi.Base;/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwb.lajitianqi.PublicFragmentActivity;
import com.lwb.lajitianqi.R;
import com.lwb.lajitianqi.Utils.FramentManages;
import com.lwb.lajitianqi.Utils.IPopBackStackListener;

import static com.lwb.lajitianqi.Constant.Bundle_key;

/**基础android.support.v4.app.Fragment，通过继承可获取或使用 里面创建的 组件 和 方法
 * @author Lemon
 * @use extends Base2Frament, 具体参考.DemoFragment
 */
public abstract class BaseFragment extends Fragment implements IPopBackStackListener {
	private static final String TAG = "BaseFrament";
	private Bundle BackBundle;
	/**
	 * 该fragment全局视图，不能在子Fragment中创建
	 */
	private View mView;
	/**
	 * 在onCreateView方法中赋值，不能在子Fragment中创建
	 */
	protected BaseFragmentActivity context = null;
	/**
	 * 添加该fragment是否已被使用并未被销毁，在onCreateView方法中赋值为true，不能在子Fragment中创建
	 */
	protected boolean isAlive = false;
	/**
	 * 添加该fragment是否在运行，不能在子Fragment中创建
	 */
	protected boolean isRunning = false;

	protected int RESULT_OK = Activity.RESULT_OK;
	protected int RESULT_CANCELED = Activity.RESULT_CANCELED;

	/**
	 * 可用于 打开activity以及activity之间的通讯（传值）等；一些通讯相关基本操作（打电话、发短信等）
	 */
	protected Intent intent = null;
	private int returnCode = -1;
	public static final String INTENT_TITLE = BaseFragmentActivity.INTENT_TITLE;
	public static final String INTENT_ID = BaseFragmentActivity.INTENT_ID;
	public static final String RESULT_DATA = BaseFragmentActivity.RESULT_DATA;

	protected PublicFragmentActivity activity;
	private FramentManages fragmentManager;
	private BasePresenter baseP;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		mView = inflater.inflate(bindLayout(), container, false);
		mView.setClickable(true);
		activity = (PublicFragmentActivity) getActivity();// 获得activity的对象
		fragmentManager = activity.fragmentManager;//片段管理
		baseP = bindPresenter();
		//每个Activity的中间标题必须都用这个id
		Bundle arguments = getArguments();
		if(arguments!=null){
			String title = (String) arguments.get(Bundle_key);
			activity.toolbar.setTitle(title);
		}

		setMiddleTitle();//设置标题
		initView();// 初始化UI
		initData();// 初始化数据
		initListener();

		return mView;
	}

	/**
	 * 中心的标题设置
	 */
	public abstract void setMiddleTitle();

	/**
	 * 绑定P层
	 *
	 * @return
	 */
	protected abstract BasePresenter bindPresenter();
	/**
	 * 绑定界面
	 *
	 * @return
	 */
	protected abstract int bindLayout();

	/**
	 * UI显示方法，必须在子类onCreateView方法内调用
	 */
	public abstract void initView();
	/**
	 * data数据方法，必须在子类onCreateView方法内调用
	 */
	public abstract void initData();
	/**
	 * listener事件监听方法，必须在子类onCreateView方法内调用
	 */
	public abstract void initListener();


	/**通过id查找并获取控件
	 * @param id
	 * @return
	 */
	public View fin(final int id) {
		return mView.findViewById(id);
	}

	public Intent getIntent() {
		return context.getIntent();
	}

	public void runOnUiThread(Runnable runnable) {
		context.runOnUiThread(runnable);
	}


	//显示与关闭进度弹窗方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**展示加载进度条,无标题
	 * @param stringResId
	 */
	public void showProgressDialog(final int stringResId){
		context.showProgressDialog(context.getResources().getString(stringResId));
	}
	/**展示加载进度条,无标题
	 * @param dialogMessage
	 */
	public void showProgressDialog(final String dialogMessage){
		context.showProgressDialog(dialogMessage);
	}
	/**展示加载进度条
	 * @param dialogTitle 标题
	 * @param dialogMessage 信息
	 */
	public void showProgressDialog(final String dialogTitle, final String dialogMessage){
		context.showProgressDialog(dialogTitle, dialogMessage);
	}

	/** 隐藏加载进度
	 */
	public void dismissProgressDialog(){
		context.dismissProgressDialog();
	}
	//显示与关闭进度弹窗方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//启动新Activity方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**打开新的Activity，向左滑入效果
	 * @param intent
	 */
	public void toActivity(final Intent intent) {
		toActivity(intent, true);
	}
	/**打开新的Activity
	 * @param intent
	 * @param showAnimation
	 */
	public void toActivity(final Intent intent, final boolean showAnimation) {
		toActivity(intent, -1, showAnimation);
	}
	/**打开新的Activity，向左滑入效果
	 * @param intent
	 * @param requestCode
	 */
	public void toActivity(final Intent intent, final int requestCode) {
		toActivity(intent, requestCode, true);
	}
	/**打开新的Activity
	 * @param intent
	 * @param requestCode
	 * @param showAnimation
	 */
	public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
		if (isAlive == false || intent == null) {
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
					context.overridePendingTransition(R.anim.right_push_in, R.anim.hold);
				} else {
					context.overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
				}
			}
		});
	}
	//启动新Activity方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//show short toast 方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param stringResId
	 */
	public void showShortToast(final int stringResId) {
		context.showShortToast(stringResId);
	}
	/**快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param string
	 */
	public void showShortToast(final String string) {
		context.showShortToast(string);
	}
	/**快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param string
	 * @param isForceDismissProgressDialog
	 */
	public void showShortToast(final String string, final boolean isForceDismissProgressDialog) {
		context.showShortToast(string, isForceDismissProgressDialog);
	}
	//show short toast 方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	public void onResume() {
		super.onResume();
		isRunning = true;
	}

	@Override
	public void onPause() {
		super.onPause();
		isRunning = false;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isRunning = false;
		isAlive = false;
		if (fragmentManager.getLastFrament() != null) {
			fragmentManager.getLastFrament().popBackListener(returnCode,BackBundle);
			fragmentManager.getLastFrament().setMiddleTitle();
		}
		if (baseP != null) {
			baseP.unBindP();
		}
	}
	/**
	 * 调用这个方法实现后退，指定返回码和数据
	 *
	 * @param returnCode
	 * @param bundle
	 */
	public void popback(int returnCode, Bundle bundle) {
		this.returnCode=returnCode;
		this.BackBundle=bundle;
		int count = activity.fragmentManager.getAllFrament().size();
		if (count > 1) {
			fragmentManager.popBackStack();
		} else {
			activity.finish();
		}
	}

	/**
	 * 调用这个方法实现后退，默认返回returnCode=-1和bundle=null
	 */
	public void popback() {
		popback(-1, null);
	}

	/**
	 * 没有设置popback的值的话默认返回-1和null
	 */
	@Override
	public void popBackListener() {
		// 没有设置popback的值的话默认返回-1和null
		popBackListener(returnCode , BackBundle);
	}

	/**
	 * 添加一个片段
	 * @param viewId 添加的控件位置
	 * @param alias  别名
	 * @param bundle 数据
	 */
	public void addFrament(int viewId, String alias, Bundle bundle, boolean isAnim) {
		fragmentManager.addFrament(viewId, activity, alias, bundle, isAnim);
	}

}