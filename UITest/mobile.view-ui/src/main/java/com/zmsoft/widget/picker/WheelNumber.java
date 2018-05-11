package com.zmsoft.widget.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.zmsoft.ui.R;

public class WheelNumber extends LinearLayout {

    private WheelView wv_number;

    public int screenheight = 80;

    /**
     * @param context
     * @param attrs
     */
    public WheelNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.numberpicker, this);
        initMainView();
    }

    /**
     * 显示.
     */
    private void initMainView() {
        wv_number = (WheelView) this.findViewById(R.id.wv_number);

    }

    /**
     * @Description: TODO 弹出日期选择器
     */
    public void initNumberPicker(int number) {
//		int textSize = 0;
//		textSize = 18;
//
//		// 时
//		wv_number.TEXT_SIZE = textSize;
//		wv_number.setAdapter(new NumericWheelAdapter(0, 99));
//		wv_number.setCyclic(true);
//		
//		wv_number.setCurrentItem(number);
//		LinearLayout.LayoutParams hourParams=(LinearLayout.LayoutParams)wv_number.getLayoutParams();
//		hourParams.height=180;
//		wv_number.setLayoutParams(hourParams);
//		wv_number.invalidate();
        initNumberPicker(number, null, null);
        // 分

    }

    /**
     * @Description: TODO 弹出日期选择器
     */
    public void initNumberPicker(int number, Integer lower, Integer higher) {
        int textSize = 0;
        textSize = 18;
        lower = lower == null ? 0 : lower;
        higher = higher == null ? 0 : higher;
        // 时
        wv_number.TEXT_SIZE = textSize;
        wv_number.setAdapter(new NumericWheelAdapter(lower, higher));
        wv_number.setCyclic(true);

        wv_number.setCurrentItem(number);
        LinearLayout.LayoutParams hourParams = (LinearLayout.LayoutParams) wv_number.getLayoutParams();
        hourParams.height = 180;
        wv_number.setLayoutParams(hourParams);
        wv_number.invalidate();
        // 分

    }

    /**
     * 取得选择的时间
     *
     * @return
     */
    public int getNumber() {

        int sb = 0;
        sb = wv_number.getCurrentItem();
        return sb;
    }
}
