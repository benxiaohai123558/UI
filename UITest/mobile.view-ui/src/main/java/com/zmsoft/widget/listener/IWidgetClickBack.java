package com.zmsoft.widget.listener;


import android.view.View;

/**
 * Widget回调
 */
public interface IWidgetClickBack {
    /**
     * 返回文本时动作.
     *
     * @param type 类型
     * @param view 当前控件
     */
    void onWidgetBack(int type, View view);

}
