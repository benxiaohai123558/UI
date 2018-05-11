package com.zmsoft.widget.switchspinner;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zmsoft.ui.R;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
/**
 * 功能：选择下拉框，更新数据
 * <p>
 * Created by danke on 2017/7/10.
 */

public class SwitchSpinner<T> extends RelativeLayout {

    private Button mSpinnerBtn;
    private ImageView mSpinnerIcon;
    private SpinnerPopupWindow<T> mSpinnerPopWindow;
    private OnSelectItemListener mOnSelectItemListener;
    private List<T> mList;

    public SwitchSpinner(Context context) {
        super(context);
        initView(context, null);
    }

    public SwitchSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public SwitchSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.switch_spinner, this);
        mSpinnerBtn = (Button) findViewById(R.id.spinner_btn);
        mSpinnerIcon = (ImageView) findViewById(R.id.spinner_icon);
        mSpinnerBtn.setOnClickListener(mOnClickListener);

        mSpinnerPopWindow = new SpinnerPopupWindow<>(context, mList, mOnItemClickListener);
        mSpinnerPopWindow.setOnDismissListener(onDismissListener);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwitchSpinner);
            for (int i = 0; i < ta.getIndexCount(); i++) {
                int attr = ta.getIndex(i);
                if (attr == R.styleable.SwitchSpinner_data) {
                    CharSequence[] textArray = ta.getTextArray(attr);
                    mList = (List<T>) Arrays.asList(textArray);
                } else if (attr == R.styleable.SwitchSpinner_backgroundButton) { // title：button 背景
                    Drawable background = ta.getDrawable(attr);
                    if (background != null) {
                        mSpinnerBtn.setBackground(background);
                    }
                } else if (attr == R.styleable.SwitchSpinner_backgroundItem) { // item 背景
                    Drawable background = ta.getDrawable(attr);
                    mSpinnerPopWindow.setBackground(background);
                } else if (attr == R.styleable.SwitchSpinner_textColorButton) { // title：text 颜色
                    ColorStateList textColor = ta.getColorStateList(attr);
                    mSpinnerBtn.setTextColor(textColor != null ? textColor : ColorStateList.valueOf(0xFFFFFF));
                } else if (attr == R.styleable.SwitchSpinner_textColorItem) { // item:text 颜色
                    ColorStateList textColor = ta.getColorStateList(attr);
                    mSpinnerPopWindow.setTextColor(textColor != null ? textColor : ColorStateList.valueOf(0xFFFFFF));
                } else if (attr == R.styleable.SwitchSpinner_colorDivider) { // 分割线颜色
                    Drawable background = ta.getDrawable(attr);
                    if (background != null) {
                        mSpinnerPopWindow.setColorDivider(background);
                    }
                } else if (attr == R.styleable.SwitchSpinner_icon) { // 箭头
                    Drawable background = ta.getDrawable(attr);
                    if (background != null) {
                        mSpinnerIcon.setBackground(background);
                    }
                }
            }
            ta.recycle();
        }
        setImageShow();
        setSpinnerText(0);
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setList(List<T> list, int position) {
        this.mList = list;
        mSpinnerPopWindow.setList(list);
        setImageShow();
        if (list.size() > position) {
            setSpinnerText(position);
        } else {
            setSpinnerText(0);
            if (mOnSelectItemListener != null) {
                mOnSelectItemListener.onItemClick(0, mList.get(0).toString());
            }
        }
    }

    /**
     * 设置下拉框的图片是否显示，如果数据为空则不显示
     */
    public void setImageShow() {
        if (mList == null || mList.isEmpty()) {
            mSpinnerIcon.setVisibility(GONE);
        } else {
            mSpinnerIcon.setVisibility(VISIBLE);
        }
    }

    /**
     * 同步按钮显示的文本
     *
     * @param position
     */
    public void setSpinnerText(int position) {
        if (mList == null || mList.isEmpty()) {
            mSpinnerBtn.setText("");
        } else {
            String selectItem = mList.get(position).toString();
            mSpinnerBtn.setText(selectItem);
        }
    }

    /**
     * 显示PopupWindow
     */
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.spinner_btn) {
                mSpinnerPopWindow.setWidth(mSpinnerBtn.getWidth());
                mSpinnerPopWindow.showAsDropDown(mSpinnerBtn);
                imageRotate(0f, 180f);
            }
        }
    };

    public String getSelectedText(){
        return mSpinnerBtn.getText().toString().trim();
    }

    /**
     * PopupWindow中的ListView的点击事件
     */
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinnerPopWindow.dismiss();
            setSpinnerText(position);
            if (mList != null && !mList.isEmpty()) {
                if (mOnSelectItemListener != null) {
                    mOnSelectItemListener.onItemClick(position, mList.get(position).toString());
                }
            }
        }
    };

    /**
     * PopupWindow消失监听
     */
    private PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            imageRotate(180f, 0f);
        }
    };

    private void imageRotate(float from, float to) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mSpinnerIcon, "rotation", from, to);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    public OnSelectItemListener getOnSelectItemListener() {
        return mOnSelectItemListener;
    }

    public void setOnSelectItemListener(OnSelectItemListener mOnSelectItemListener) {
        this.mOnSelectItemListener = mOnSelectItemListener;
    }

    public interface OnSelectItemListener {
        /**
         * 条目选中的数据回调
         *
         * @param position
         * @param itemStr
         */
        void onItemClick(int position, String itemStr);
    }
}
