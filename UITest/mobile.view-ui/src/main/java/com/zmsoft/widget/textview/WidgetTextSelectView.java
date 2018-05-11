package com.zmsoft.widget.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.zmsoft.ui.R;
import com.zmsoft.widget.textview.WidgetBaseTextView;

/**
 * 描述 ：WidgetTextView请选择 时情况展示
 * Created by huangyu on 2017/8/7.
 *
 * @version v1.0
 */

public class WidgetTextSelectView extends WidgetBaseTextView {
    public WidgetTextSelectView(Context context) {
        super(context);
    }

    public WidgetTextSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetTextSelectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void loadinit() {
        super.loadinit();
        mTxtContent.setHint(getResources().getString(R.string.please_select));
        mTxtContent.setHintTextColor(getResources().getColor(R.color.gray));
        mIconArrowLeft.setVisibility(View.VISIBLE);
    }
}
