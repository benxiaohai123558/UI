package com.zmsoft.widget.textview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmsoft.ui.R;
import com.zmsoft.widget.CommonItem;
import com.zmsoft.widget.ICommonItemType;

/**
 * 描述 ：文字展示.
 * Created by huangyu on 2017/7/26.
 *
 * @version v1.0
 */

public class WidgetBaseTextView extends CommonItem {
    RelativeLayout viewValue;
    TextView mTxtContent;
    TextView mTxtContent2;
    ImageView mViewChild;
    TextView mTxtMemo;
    ImageView mIconArrowLeft;
    View txtMemo_line;

    public WidgetBaseTextView(Context context) {
        this(context, null);
    }

    public WidgetBaseTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WidgetBaseTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 加载布局.
     *
     * @param context
     */
    @Override
    public View initContext(Context context) {
        // 导入布局
        View view = LayoutInflater.from(context).inflate(R.layout.widget_text_view, this, true);
        viewValue = (RelativeLayout) view.findViewById(R.id.main_layout);
        mTxtContent = (TextView) findViewById(R.id.txtContent);
        mTxtContent2 = (TextView) view.findViewById(R.id.txtContent2);
        mViewChild = (ImageView) view.findViewById(R.id.viewChild);
        mTxtMemo = (TextView) view.findViewById(R.id.txtMemo);
        mIconArrowLeft = (ImageView) view.findViewById(R.id.icon_arrow_left);
        txtMemo_line = view.findViewById(R.id.txtMemo_line);
        return view;
    }

    /**
     * 初始化页面设值.
     */
    @Override
    public void loadinit() {
        if (inputType == ICommonItemType.SHOW_ONLY) {
            mTxtContent.setTextColor(getResources().getColor(R.color.black));
        }
        if (content != -1) {
            mTxtContent.setText(content);
            oldValue = mTxtContent.getText().toString();
        }
        if (content2 != -1) {
            mTxtContent2.setVisibility(View.VISIBLE);
            mTxtContent2.setText(content2);
        }
        if (memo != -1) {
            mTxtMemo.setVisibility(View.VISIBLE);
            mTxtMemo.setText(memo);
        }
        if (child) {
            mViewChild.setVisibility(View.VISIBLE);
        }
        if (arrow_left) {
            mIconArrowLeft.setVisibility(View.VISIBLE);
        }
        if (hint != -1) {
            mTxtContent.setHint(hint);
        }
        if (hint_color != -1) {
            mTxtContent.setHintTextColor(hint_color);
        }
        if (memo_color != -1) {
            mTxtMemo.setTextColor(memo_color);
        }
        if (img_right != -1) {
            mIconArrowLeft.setImageResource(img_right);
            mIconArrowLeft.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 输入完毕后的一系列操作.
     *
     * @param text
     */
    public void onTextBack(String text) {
        if (text == null) {
            if (newValue == null) {
                return;
            }
        } else if (text.equals(newValue)) {
            return;
        }
        newValue = text;
        if (controlListener != null) {
            controlListener.onControlEditCallBack(this, oldValue, newValue);
        }
    }

    public void setOldText(String text) {
        oldValue = text;
        mTxtContent.setText(text);
        newValue = text;
    }

    public void setNewText(String text) {
        mTxtContent.setText(text);
        onTextBack(text);
    }

    public String getNewText() {
        return newValue;
    }

    public void setNewValue(String str) {
        newValue = str;
    }


    public void setMemoColor(int color) {
        mTxtMemo.setTextColor(color);
    }

    public void setContectColor(int color) {
        mTxtContent.setTextColor(color);
    }

    public void setMemoText(String text) {
        if (!isBlank(text)) {
            mTxtMemo.setVisibility(View.VISIBLE);
            mTxtMemo.setText(text);
        } else {
            mTxtMemo.setVisibility(View.GONE);
        }
    }

    //设置向下图标
    public void setImageDirectionDown() {
        mIconArrowLeft.setImageResource(R.drawable.icon_next_down);
    }

    public void setViewTextName(String text) {
        mViewName.setText(text);
    }

    public void setIsChild(boolean isChild) {
        this.child = isChild;
        mViewChild.setVisibility(isChild ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置输入格式
     *
     * @param showType
     */
    public void setInputTypeShow(int showType) {
        this.inputType = showType;
        if (inputType == ICommonItemType.SHOW_ONLY) {
            mTxtContent.setTextColor(getResources().getColor(R.color.black));
            mTxtContent.setHint("");
            mIconArrowLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 设置输入最大长度
     *
     * @param maxWidth
     */
    public void setTextMaxWidth(int maxWidth) {
        if (mTxtContent != null) {
            mTxtContent.setMaxWidth(maxWidth);
        }
    }

    public void setSingleLine() {
        if (mTxtContent != null) {
            mTxtContent.setSingleLine(true);
            mTxtContent.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    public TextView getTxtContent() {
        return mTxtContent;
    }

    public TextView getTxtMemo() {
        return mTxtMemo;
    }

    public ImageView getIconArrowLeft() {
        return mIconArrowLeft;
    }

    public void setMemoLine(boolean isVis) {
        txtMemo_line.setVisibility(isVis ? View.VISIBLE : View.GONE);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 判断内容是否改变
     *
     * @return
     */
    public boolean isChanged() {
        if (newValue != null) {
            return !newValue.equals(oldValue);
        } else {
            return false;
        }
    }
}
