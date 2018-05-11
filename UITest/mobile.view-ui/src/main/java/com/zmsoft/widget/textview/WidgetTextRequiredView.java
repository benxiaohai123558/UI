package com.zmsoft.widget.textview;

import android.content.Context;
import android.util.AttributeSet;

import com.zmsoft.ui.R;
import com.zmsoft.widget.textview.WidgetBaseTextView;

/**
 * 描述 ：WidgetTextView必填 时情况展示
 * Created by huangyu on 2017/8/7.
 *
 * @version v1.0
 */

public class WidgetTextRequiredView extends WidgetBaseTextView {
    public WidgetTextRequiredView(Context context) {
        super(context);
    }

    public WidgetTextRequiredView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetTextRequiredView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void loadinit() {
        super.loadinit();
        mTxtContent.setHint(getResources().getString(R.string.required_input));
        mTxtContent.setHintTextColor(getResources().getColor(R.color.red));
    }
}
