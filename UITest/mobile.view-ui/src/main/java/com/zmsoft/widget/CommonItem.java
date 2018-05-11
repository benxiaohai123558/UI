package com.zmsoft.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zmsoft.ui.R;
import com.zmsoft.widget.listener.IEditViewFocusChangeListener;
import com.zmsoft.widget.listener.IWidgetClickListener;
import com.zmsoft.widget.listener.OnControlListener;

/**
 * 描述 ：输入显示项.
 * Created by huangyu on 2017/7/26.
 *
 * @version v1.0
 */

public abstract class CommonItem extends FrameLayout {

    protected TextView mViewName;

    ViewGroup main_layout;

    LinearLayout mViewNameLayout;

    protected IWidgetClickListener widgetClickListener = null;

    /**
     * <code>绑定文本内容</code>.
     */
    protected int content;
    /**
     * <code>数字键盘标题</code>.
     */
    protected String keyboardTitle;
    /**
     * <code>数字键盘标记</code>.
     */
    protected int keyboardType;
    /**
     * <code>原始值</code>.
     */
    protected String oldValue;
    /**
     * <code>修改后值</code>.
     */
    protected String newValue;
    /**
     * <code>最小长度</code>
     */
    protected int minLength;
    /**
     * <code>最大长度</code>
     */
    protected int maxLength;
    /**
     * <code>最小值数字</code>
     */
    protected int min;
    /**
     * <code>最大值数字</code>
     */
    protected int max;
    /**
     * <code>名称</code>
     */
    protected int name;
    /**
     * <code>第二部分名称(尾部分有时候单位有用)</code>
     */
    protected int content2;
    /**
     * <code>第二行显示的memo信息</code>
     */
    protected int memo;
    /**
     * <code>备注颜色</code>
     */
    protected int memo_color;
    /**
     * <code>数据类型</code>
     */
    protected int inputType;
    /**
     * <code>提示颜色</code>
     */
    protected int hint_color;
    /**
     * <code>提示信息</code>
     */
    protected int hint;
    /**
     * <code>提示信息</code>
     */
    protected boolean isPassword;
    /**
     * <code>提示信息</code>
     */
    protected boolean arrow_left;
    /**
     * <code>是否允许负数</code>
     */
    protected boolean isAllowNegative;
    /**
     * <code>是否子节点</code>
     */
    protected boolean child;
    /**
     * <code>数字键盘是否要有小数点</code>
     */
    protected boolean candot;
    /**
     * <code>右侧箭头图标resId</code>
     */
    protected int img_right;
    /**
     * 内容改变后回调事件.
     */
    protected OnControlListener controlListener;

    protected IEditViewFocusChangeListener iEditViewFocusChangeListener;

    public CommonItem(Context context) {
        super(context);
        initView(context);
    }

    public CommonItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        this.readStyleParameters(context, attrs);
    }

    public CommonItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
        this.readStyleParameters(context, attrs);
    }

    private void readStyleParameters(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CommonItem);
        try {
            name = array.getResourceId(R.styleable.CommonItem_item_name, -1);
            content = array.getResourceId(R.styleable.CommonItem_item_context, -1);
            keyboardTitle = array.getString(R.styleable.CommonItem_item_key_title);
            if (keyboardTitle == null) {
                keyboardTitle = " ";
            }
            keyboardType = array.getInteger(R.styleable.CommonItem_item_key_type, -1);
            maxLength = array.getInteger(R.styleable.CommonItem_item_max_length, -1);
            minLength = array.getInteger(R.styleable.CommonItem_item_min_length, -1);
            min = array.getInteger(R.styleable.CommonItem_item_min, -1);
            max = array.getInteger(R.styleable.CommonItem_item_max, -1);
            inputType = array.getInteger(R.styleable.CommonItem_item_type, ICommonItemType.STRING);
            content2 = array.getResourceId(R.styleable.CommonItem_item_context2, -1);
            memo = array.getResourceId(R.styleable.CommonItem_item_memo, -1);
            memo_color = array.getInteger(R.styleable.CommonItem_item_memo_color, -1);
            hint_color = array.getInteger(R.styleable.CommonItem_item_hint_color, -1);
            hint = array.getResourceId(R.styleable.CommonItem_item_hint, -1);
            isPassword = array.getBoolean(R.styleable.CommonItem_item_password, false);
            arrow_left = array.getBoolean(R.styleable.CommonItem_item_arrow_left, false);
            isAllowNegative = array.getBoolean(R.styleable.CommonItem_item_allow_negative, false);
            child = array.getBoolean(R.styleable.CommonItem_item_child, false);
            candot = array.getBoolean(R.styleable.CommonItem_can_dot, true);
            img_right = array.getResourceId(R.styleable.CommonItem_item_right_img, -1);
            if (!isInEditMode()) {
                mViewName.setText(name);
                this.setFocusableInTouchMode(true);
                loadinit();
            }
        } finally {
            array.recycle();
        }
    }

    /**
     * 加载布局.
     *
     * @param context
     */
    public abstract View initContext(Context context);

    /**
     * 加载布局.
     *
     * @param context
     */
    private void initView(Context context) {
        View view = initContext(context);
        mViewName = (TextView) view.findViewById(R.id.viewName);
        mViewNameLayout = (LinearLayout) view.findViewById(R.id.viewName_layout);
        main_layout = (ViewGroup) view.findViewById(R.id.main_layout);
        if (!isInEditMode()) {
            main_layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setOnWidgetClick();
                }
            });
        }

    }

    public void setOnWidgetClick() {
        this.requestFocus();
        if (widgetClickListener != null) {
            widgetClickListener.onWidgetClick(this);
        }
    }

    /**
     * 初始化页面设值.
     */
    public abstract void loadinit();

    /**
     * 重新对
     *
     * @param text
     */

    public void setMviewName(String text) {
        mViewName.setText(text);
    }

    /**
     * 得到控件的名字：name
     */
    public String getMviewName() {
        return mViewName.getText() != null ? mViewName.getText().toString() : "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }


    public void setOnControlListener(OnControlListener controlListener) {
        this.controlListener = controlListener;
    }

    /**
     * 设置点击事件.
     *
     * @param widgetClickListener
     */
    public void setWidgetClickListener(IWidgetClickListener widgetClickListener) {
        this.widgetClickListener = widgetClickListener;
    }

    /**
     * 监听组件焦点
     *
     * @param iEditViewFocusChangeListener
     */
    public void setOnFocusChangeListener(IEditViewFocusChangeListener iEditViewFocusChangeListener) {
        this.iEditViewFocusChangeListener = iEditViewFocusChangeListener;
    }

    /**
     * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
     * <p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace
     * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     * <p>
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     * not empty and not null and not whitespace
     * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
