package com.example.an;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Context context = null;
	ViewPager pager = null;
	TextView t1, t2, t3;
	View view1, view2, view3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = MainActivity.this;
		initTextView();
		initPagerViewer();

	}

	/**
	 * 初始化标题
	 */
	private void initTextView() {
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		t3 = (TextView) findViewById(R.id.text3);
		t1.setOnClickListener(this);
		t2.setOnClickListener(this);
		t3.setOnClickListener(this);

	}

	/**
	 * 初始化PageViewer
	 */
	private void initPagerViewer() {
		pager = (ViewPager) findViewById(R.id.viewpage);
		LayoutInflater li = LayoutInflater.from(this);
		final ArrayList<View> list = new ArrayList<View>();
		view1 = li.inflate(R.layout.tab1, null);
		view2 = li.inflate(R.layout.tab2, null);
		view3 = li.inflate(R.layout.tab3, null);
		list.add(view1);
		list.add(view2);
		list.add(view3);

		pager.setAdapter(new MyPagerAdapter(list));
		pager.setCurrentItem(0);
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * Pager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {
		List<View> list = new ArrayList<View>();

		public MyPagerAdapter(ArrayList<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {// 销毁view
			ViewPager pViewPager = ((ViewPager) container);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) { // 初始化Item
																// 通过位置arg1拿到view
			ViewPager pViewPager = ((ViewPager) arg0);
			pViewPager.addView(list.get(arg1));
			return list.get(arg1);
		}
	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {


		@Override
		public void onPageSelected(int arg0) {//通过view 的位置来更改按钮的text
			resert();
			int currentItems = pager.getCurrentItem(); 
			switch (currentItems) {
			
			case 0:
				t1.setText("选中");
				break;
			case 1:
				t2.setText("选中");
				break;
			case 2:
				t3.setText("选中");
				break;

			default:
				break;
			}
		
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	// 点击按钮
	public void button2(View v) {
		Toast.makeText(this, "点击了我", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		resert();

		switch (v.getId()) {
		case R.id.text1:
			pager.setCurrentItem(0);
			t1.setText("朕选中");
			break;
		case R.id.text2:
			pager.setCurrentItem(1);
			t2.setText("朕选中");
			break;
		case R.id.text3:
			pager.setCurrentItem(2);
			t3.setText("朕选中");
			break;
		}

	}
	//初始化按钮的选中情况
	public void resert()
	{
		t1.setText("发榜招聘");
		t2.setText("我要兼职");
		t3.setText("个人中心");
	}

}