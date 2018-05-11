package com.zmsoft.widget.listener;

/**
 * 描述 ：文本回调 外部调用
 * Created by huangyu on 2017/8/10.
 *
 * @version v1.0
 */

public interface IWidgetCallBack {
    /**
     * 返回文本时动作.
     * @param text 返回的文本.
     */
    void onWidgetBack(String text, Short type);
}
