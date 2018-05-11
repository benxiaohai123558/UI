package com.zmsoft.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 矩形图章
 * Created by danke on 2017/5/31
 */
public class RectangleStampView extends RelativeLayout {

    private Context mContext;
    private View mView;
    private TextView mStampContent;
    private ImageView mStampIcon;

    public RectangleStampView(Context context) {
        super(context);
        this.mContext = context;
        init(context, null);
    }

    public RectangleStampView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RectangleStampView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        mView = View.inflate(mContext, R.layout.rectangle_stamp_view, this);
        mStampContent = (TextView) mView.findViewById(R.id.tv_stamp_content);
        mStampIcon = (ImageView) mView.findViewById(R.id.iv_stamp_icon);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RectangleStampView);
            for (int i = 0; i < ta.getIndexCount(); i++) {
                int attr = ta.getIndex(i);
                if (attr == R.styleable.RectangleStampView_stamp_bg) {
                    mStampIcon.setImageDrawable(ta.getDrawable(attr));
                } else if (attr == R.styleable.RectangleStampView_stamp_content) {
                    mStampContent.setText(ta.getString(attr));
                } else if (attr == R.styleable.RectangleStampView_stamp_color) {
                    mStampContent.setTextColor(ta.getColor(attr, 0x000000));
                } else if (attr == R.styleable.RectangleStampView_stamp_text_size) {
                    mStampContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelOffset(attr, getResources().getDimensionPixelOffset(R.dimen.px38)));
                }
            }
            ta.recycle();
        }
    }

    /**
     * 设置内容
     *
     * @param stampContent
     */
    public void setStampContent(String stampContent) {
        if (!TextUtils.isEmpty(stampContent)) {
            mStampContent.setText(stampContent);
        }
    }

    /**
     * 设置图标
     *
     * @param resId
     */
    public void setStampIcon(int resId) {
        mStampIcon.setImageResource(resId);
    }

    /**
     * 获取内容
     *
     * @return
     */
    public String getStampContent() {
        String stampContent = null;
        if (mStampContent != null) {
            stampContent = mStampContent.getText().toString().trim();
        }
        return stampContent;
    }

    /**
     * 设置内容颜色
     *
     * @param resId
     */
    public void setStampContentColor(int resId) {
        if (mStampContent != null) {
            mStampContent.setTextColor(getResources().getColor(resId));
        }
    }

}
