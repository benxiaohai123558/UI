package com.zmsoft.widget.switchbtn;

import android.content.Context;
import android.util.AttributeSet;

import com.zmsoft.widget.switchbtn.WidgetBaseSwichBtn;

/**
 * 描述 ：WidgetSwichBtn 小开关
 * Created by huangyu on 2017/8/8.
 *
 * @version v1.0
 */

public class WidgetSwichSmallBtn extends WidgetBaseSwichBtn {
    public static  final float SWICH_SMALL_BTN_SCALE_X = 0.9f;
    public static  final float SWICH_SMALL_BTN_SCALE_Y = 0.9f;

    public WidgetSwichSmallBtn(Context context) {
        super(context);
    }

    public WidgetSwichSmallBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetSwichSmallBtn(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void loadinit() {
        super.loadinit();
        mBoolBtn.setScaleX(SWICH_SMALL_BTN_SCALE_X);
        mBoolBtn.setScaleY(SWICH_SMALL_BTN_SCALE_Y);
    }
}
