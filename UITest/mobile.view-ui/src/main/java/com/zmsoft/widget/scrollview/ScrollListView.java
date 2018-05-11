package com.zmsoft.widget.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ScrollListView extends ListView {

	public ScrollListView(Context context) {
		super(context);
	}

	public ScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 加上下面的话即可实现listview在scrollview中滑动
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
