package com.zmsoft.widget.listener;

import com.zmsoft.widget.scrollview.ObservableScrollView;

/**
 * 自定义ScrollView滚动的监听事件
 * @author Administrator
 *
 */
public interface IScrollViewListener {

	void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
	
	ObservableScrollView getObservableScrollView();
	
}
