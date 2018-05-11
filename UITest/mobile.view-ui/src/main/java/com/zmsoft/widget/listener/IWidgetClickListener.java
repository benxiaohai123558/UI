package com.zmsoft.widget.listener;

import android.view.View;

/**
 * 控件点击操作完毕后返回值接口.
 * @version $Revision$.
 */
public interface IWidgetClickListener {
    /**
     * 点击事件
     * @param view 返回操作视图
     */
	void onWidgetClick(View view);
}
