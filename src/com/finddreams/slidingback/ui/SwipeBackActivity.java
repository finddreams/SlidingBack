package com.finddreams.slidingback.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.finddreams.slidingback.R;

/**
 * @Description:滑动返回，随着指尖滑动返回
 * @author http://blog.csdn.net/finddreams
 */
public abstract class SwipeBackActivity extends Activity implements
		SwipeBackLayout.SwipeBackListener {

	private SwipeBackLayout swipeBackLayout;
	private ImageView ivShadow;

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(getContainer());
		View view = LayoutInflater.from(this).inflate(layoutResID, null);
		swipeBackLayout.addView(view);
	}

	private View getContainer() {
		RelativeLayout container = new RelativeLayout(this);
		swipeBackLayout = new SwipeBackLayout(this);
		swipeBackLayout.setOnSwipeBackListener(this);
		ivShadow = new ImageView(this);
		ivShadow.setBackgroundColor(getResources().getColor(R.color.black_p50));
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		container.addView(ivShadow, params);
		container.addView(swipeBackLayout);
		return container;
	}

	public void setDragEdge(SwipeBackLayout.DragEdge dragEdge) {
		swipeBackLayout.setDragEdge(dragEdge);
	}

	public SwipeBackLayout getSwipeBackLayout() {
		return swipeBackLayout;
	}

	@SuppressLint("NewApi")
	@Override
	public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
		ivShadow.setAlpha(1 - fractionScreen);
	}

}
