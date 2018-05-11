package com.zmsoft.widget.listener;

import android.view.View;

/**
 * 控件点击操作完毕后返回值接口.
 * @author <a href="mailto:157195005@qq.com">陈杨</a>.
 * @version $Revision$.
 */
public interface OnControlListener {
    /**
     * 点击事件并且得到原值和最新值.
     * @param view 返回操作视图
     * @param oldObj 返回原值
     * @param newObj 返回新值
     */
	void onControlEditCallBack(View view, Object oldObj, Object newObj);
}
