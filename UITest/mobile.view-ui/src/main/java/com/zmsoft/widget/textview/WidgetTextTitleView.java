package com.zmsoft.widget.textview;

import android.content.Context;
import android.util.AttributeSet;

import com.zmsoft.ui.R;
import com.zmsoft.widget.textview.WidgetBaseTextView;

/**
 * 描述 ：WidgetTextView标题展示
 * Created by huangyu on 2017/8/7.
 *
 * @version v1.0
 */

public class WidgetTextTitleView extends WidgetBaseTextView {
    public WidgetTextTitleView(Context context) {
        super(context);
    }

    public WidgetTextTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetTextTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void loadinit() {
        super.loadinit();
        mViewName.setTextSize(getResources().getDimensionPixelSize(R.dimen.px18));
    }
}
