package com.zmsoft.widget.textview;

import android.content.Context;
import android.util.AttributeSet;

import com.zmsoft.ui.R;
import com.zmsoft.widget.textview.WidgetBaseTextView;

/**
 * 描述 ：WidgetTextView 可不填 时情况展示
 * Created by huangyu on 2017/8/7.
 *
 * @version v1.0
 */

public class WidgetTextNoRequiredView extends WidgetBaseTextView {
    public WidgetTextNoRequiredView(Context context) {
        super(context);
    }

    public WidgetTextNoRequiredView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetTextNoRequiredView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void loadinit() {
        super.loadinit();
        mTxtContent.setHint(getResources().getString(R.string.not_required_input));
        mTxtContent.setHintTextColor(getResources().getColor(R.color.gray));
    }
}
