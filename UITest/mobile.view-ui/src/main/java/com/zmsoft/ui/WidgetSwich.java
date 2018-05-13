package com.zmsoft.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.zmsoft.widget.listener.IWidgetClickListener;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

/**
 * Author :Guazi
 * Time   :18/5/13
 * Desc   : 自定义开关控件
 */

public class WidgetSwich extends LinearLayout {

    /**
     * <code>是</code>.
     */
    public static final Short TRUE = (short) 1;

    /**
     * <code>否</code>.
     */
    public static final Short FALSE = (short) 0;

    private Context context;

    private TextView titleTv, contextTv;//标题，类容

    private Button toggleBtn;

    private IWidgetClickListener clickListener;//点击事件

    public WidgetSwich(Context context) {
        super(context);
        this.initView(context);
    }

    public WidgetSwich(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
        this.readStyleParameters(context, attrs);
    }

    public WidgetSwich(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
        this.readStyleParameters(context, attrs);
    }

    /**
     * 加载布局.
     *
     * @param context
     */
    private void initView(Context context) {
        this.context = context;
        // 导入布局
        View view = LayoutInflater.from(context).inflate(R.layout.layout_widget_switch, this, true);
        this.titleTv = (TextView) view.findViewById(R.id.txt_title);
        this.contextTv = (TextView) view.findViewById(R.id.txt_context);
        this.toggleBtn = (Button) view.findViewById(R.id.btn_toggle);
        setSwitchValue(false);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Short value = getSwitchValue();
                if (value == TRUE) {
                    value = FALSE;
                } else {
                    value = TRUE;
                }
                setSwitchValue(value);
            }
        });
    }

    private void readStyleParameters(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WidgetSwich);
            for (int i = 0; i < ta.getIndexCount(); i++) {
                int attr = ta.getIndex(i);
                if (attr == R.styleable.WidgetSwich_ws_title) {//标题内容
                    titleTv.setText(ta.getString(attr));
                } else if (attr == R.styleable.WidgetSwich_ws_title_color) {//标题颜色
                    ColorStateList titleColor = ta.getColorStateList(attr);
                    titleTv.setTextColor(titleColor != null ? titleColor : ColorStateList.valueOf(0X000000));
                } else if (attr == R.styleable.WidgetSwich_ws_title_size) {//标题字体大小
                    float textSize = ta.getDimension(attr, -1);
                    if (textSize > 0) {
                        titleTv.setTextSize(COMPLEX_UNIT_PX, textSize);//收银字体大小使用的px，所以转换的时候需要制定规则，否则会按sp转换
                    }
                } else if (attr == R.styleable.WidgetSwich_ws_title_style) {//标题字体是否加粗
                    boolean visibile = ta.getBoolean(attr, false);
                    TextPaint tp = titleTv.getPaint();
                    tp.setFakeBoldText(visibile);
                } else if (attr == R.styleable.WidgetSwich_ws_context) {//内容
                    contextTv.setText(ta.getString(attr));
                } else if (attr == R.styleable.WidgetSwich_ws_context_color) {//内容字体颜色
                    ColorStateList titleColor = ta.getColorStateList(attr);
                    contextTv.setTextColor(titleColor != null ? titleColor : ColorStateList.valueOf(0X666666));
                } else if (attr == R.styleable.WidgetSwich_ws_context_size) {//内容字体大小
                    float textSize = ta.getDimension(attr, -1);
                    if (textSize > 0) {
                        contextTv.setTextSize(COMPLEX_UNIT_PX, textSize);//收银字体大小使用的px，所以转换的时候需要制定规则，否则会按sp转换
                    }
                } else if (attr == R.styleable.WidgetSwich_ws_context_visible) {//提示内容
                    boolean visibile = ta.getBoolean(attr, false);
                    this.showContext(visibile);
                } else if (attr == R.styleable.WidgetSwich_ws_context_paddingleft) {
                    int paddingLeft = ta.getDimensionPixelSize(attr, -1);
                    if (paddingLeft > 0) {
                        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) contextTv.getLayoutParams();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            lp.setMarginStart(paddingLeft);
                        }
                    }
                } else if (attr == R.styleable.WidgetSwich_ws_swich_bg) {//开关按钮样式
                    Drawable draw = ta.getDrawable(attr);
                    if (draw != null) {
                        toggleBtn.setBackgroundDrawable(draw);
                    }
                } else if (attr == R.styleable.WidgetSwich_ws_swich_visible) {//是否显示开关
                    boolean visibile = ta.getBoolean(attr, false);
                    this.showToggleBtn(visibile);
                }
            }
            ta.recycle();
        }
    }

    public void setWidgetClickListener(IWidgetClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * 设置标题内容
     *
     * @param resId
     */
    public void setTitleValue(int resId) {
        this.titleTv.setText(resId);
    }

    /**
     * 设置标题内容
     *
     * @param value
     */
    public void setTitleValue(String value) {
        this.titleTv.setText(value);
    }

    /**
     * 获取标题值
     *
     * @return
     */
    public String getTitleValue() {
        return titleTv.getText().toString().trim();
    }

    /**
     * 设置标题字体颜色
     *
     * @param color
     */
    public void setTitleColor(int color) {
        this.titleTv.setTextColor(color);
    }

    /**
     * 设置标题字体大小
     *
     * @param size
     */
    public void setTitleSize(float size) {
        this.titleTv.setTextSize(size);
    }

    /**
     * 设置标题是否加粗
     *
     * @param value
     */
    public void setTitleBold(boolean value) {
        TextPaint tp = titleTv.getPaint();
        tp.setFakeBoldText(value);
    }

    /**
     * 设置内容
     *
     * @param resId
     */
    public void setContextValue(int resId) {
        this.contextTv.setText(resId);
    }

    /**
     * 设置内容
     *
     * @param value
     */
    public void setContextValue(String value) {
        this.contextTv.setText(value);
    }

    /**
     * 获取内容
     *
     * @return
     */
    public String getContextValue() {
        return this.contextTv.getText().toString().trim();
    }

    /**
     * 设置内容字体颜色
     *
     * @param color
     */
    public void setContextColor(int color) {
        this.contextTv.setTextColor(color);
    }

    /**
     * 设置内容字体大小
     *
     * @param size
     */
    public void setContextSize(float size) {
        this.contextTv.setTextSize(size);
    }

    /**
     * 是否显示提示内容
     *
     * @param isVisibility
     */
    public void showContext(boolean isVisibility) {
        if (isVisibility) {
            contextTv.setVisibility(View.VISIBLE);
        } else {
            contextTv.setVisibility(View.GONE);
        }
    }


    /**
     * 是否显示删除按钮
     *
     * @param isVisibility
     */
    public void showToggleBtn(boolean isVisibility) {
        if (isVisibility) {
            toggleBtn.setVisibility(View.VISIBLE);
        } else {
            toggleBtn.setVisibility(View.GONE);
        }
    }

    /**
     * 根据传入的值，设置开关按钮的状态值
     **/
    public void setSwitchValue(short value) {
        if (TRUE.equals(value)) {
            toggleBtn.setTag("1");
            toggleBtn.setBackgroundResource(R.drawable.btn_switch_on);
        } else if (FALSE.equals(value)) {
            toggleBtn.setTag("0");
            toggleBtn.setBackgroundResource(R.drawable.btn_switch_off);
        }
    }

    /**
     * 根据传入的值，设置开关按钮的状态值
     **/
    public void setSwitchValue(boolean value) {
        if (value) {
            toggleBtn.setTag("1");
            toggleBtn.setBackgroundResource(R.drawable.btn_switch_on);
        } else {
            toggleBtn.setTag("0");
            toggleBtn.setBackgroundResource(R.drawable.btn_switch_off);
        }
    }

    /**
     * 根据传入的值，设置开关按钮的状态值
     **/
    public void switchOnClick() {
        String value = String.valueOf(toggleBtn.getTag());
        if ("0".equals(value)) {
            toggleBtn.setTag("1");
            toggleBtn.setBackgroundResource(R.drawable.btn_switch_on);
        } else if ("1".equals(value)) {
            toggleBtn.setTag("0");
            toggleBtn.setBackgroundResource(R.drawable.btn_switch_off);
        }
    }

    /**
     * getter方法获得开关按钮的值值
     **/
    public short getSwitchValue() {
        if (toggleBtn != null && toggleBtn.getTag() != null) {
            String value = String.valueOf(toggleBtn.getTag());
            return Short.parseShort(value);
        }
        return FALSE;
    }
}
