package com.zmsoft.widget.switchbtn;

import android.content.Context;
import android.util.AttributeSet;

import com.zmsoft.ui.R;
import com.zmsoft.widget.switchbtn.WidgetBaseSwichBtn;

/**
 * 描述 ：WidgetSwichBtn 大开关
 * Created by huangyu on 2017/8/17.
 *
 * @version v1.0
 */

public class WidgetSwichLargeBtn extends WidgetBaseSwichBtn {
    public WidgetSwichLargeBtn(Context context) {
        super(context);
    }

    public WidgetSwichLargeBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetSwichLargeBtn(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void loadinit() {
        super.loadinit();
        mTxtMemo.setTextColor(getResources().getColor(R.color.light_gray));
    }
}
