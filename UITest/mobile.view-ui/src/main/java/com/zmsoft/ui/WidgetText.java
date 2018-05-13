package com.zmsoft.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zmsoft.widget.listener.IWidgetClickBack;
import com.zmsoft.widget.listener.IWidgetClickListener;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

/**
 * author: Guazi.
 * time  : 2018/5/9.
 * desc  : 自定义控件
 */

public class WidgetText extends LinearLayout {

    private Context context;

    private FrameLayout bgLayout;

    private TextView titleTv, contextTv;//标题，类容

    private ImageView deleteImg, arrowImg;//删除图片，向右箭头

    private IWidgetClickListener clickListener;//点击事件

    private IWidgetClickBack deleteClickBack;//删除事件

    private int type;

    public WidgetText(Context context) {
        super(context);
        this.initView(context);
    }

    public WidgetText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
        this.readStyleParameters(context, attrs);
    }

    public WidgetText(Context context, AttributeSet attrs, int defStyleAttr) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_widget_text, this, true);
        this.bgLayout = (FrameLayout) view.findViewById(R.id.layout);
        this.titleTv = (TextView) view.findViewById(R.id.txt_title);
        this.contextTv = (TextView) view.findViewById(R.id.txt_context);
        this.deleteImg = (ImageView) view.findViewById(R.id.img_delete);
        this.arrowImg = (ImageView) view.findViewById(R.id.img_arrow);
        this.initButton();
    }

    private void initButton() {
        this.bgLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onWidgetClick(WidgetText.this);
                }
            }
        });
        this.deleteImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                contextTv.setText(null);
                showDeleteImg(false);
                if (deleteClickBack != null) {
                    deleteClickBack.onWidgetBack(type, WidgetText.this);
                }
            }
        });
    }

    private void readStyleParameters(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WidgetText);
            for (int i = 0; i < ta.getIndexCount(); i++) {
                int attr = ta.getIndex(i);
                if (attr == R.styleable.WidgetText_wt_title) {//标题内容
                    titleTv.setText(ta.getString(attr));
                } else if (attr == R.styleable.WidgetText_wt_title_color) {//标题字体颜色
                    ColorStateList titleColor = ta.getColorStateList(attr);
                    titleTv.setTextColor(titleColor != null ? titleColor : ColorStateList.valueOf(0X000000));
                } else if (attr == R.styleable.WidgetText_wt_title_size) {//标题字体大小
                    float textSize = ta.getDimension(attr, -1);
                    if (textSize > 0) {
                        titleTv.setTextSize(COMPLEX_UNIT_PX, textSize);//收银字体大小使用的px，所以转换的时候需要制定规则，否则会按sp转换
                    }
                } else if (attr == R.styleable.WidgetText_wt_title_style) {//标题字体是否加粗
                    boolean visibile = ta.getBoolean(attr, false);
                    TextPaint tp = titleTv.getPaint();
                    tp.setFakeBoldText(visibile);
                } else if (attr == R.styleable.WidgetText_wt_context) {//内容
                    contextTv.setText(ta.getString(attr));
                } else if (attr == R.styleable.WidgetText_wt_context_color) {//内容字体颜色
                    ColorStateList titleColor = ta.getColorStateList(attr);
                    contextTv.setTextColor(titleColor != null ? titleColor : ColorStateList.valueOf(0X0066AA));
                } else if (attr == R.styleable.WidgetText_wt_context_size) {//内容字体大小
                    float textSize = ta.getDimension(attr, -1);
                    if (textSize > 0) {
                        contextTv.setTextSize(COMPLEX_UNIT_PX, textSize);
                    }
                } else if (attr == R.styleable.WidgetText_wt_context_hit) {//提示内容
                    contextTv.setHint(ta.getString(attr));
                } else if (attr == R.styleable.WidgetText_wt_context_hit_color) {//提示内容字体颜色
                    ColorStateList titleColor = ta.getColorStateList(attr);
                    contextTv.setHintTextColor(titleColor != null ? titleColor : ColorStateList.valueOf(0X666666));
                } else if (attr == R.styleable.WidgetText_wt_delete_src) {//删除按钮样式
                    Drawable deleteDraw = ta.getDrawable(attr);
                    if (deleteDraw != null) {
                        deleteImg.setImageDrawable(deleteDraw);
                    }
                } else if (attr == R.styleable.WidgetText_wt_delete_visible) {//是否显示删除按钮
                    boolean visibile = ta.getBoolean(attr, false);
                    this.showDeleteImg(visibile);
                } else if (attr == R.styleable.WidgetText_wt_arrow_src) {//向右箭头样式
                    Drawable arrowDraw = ta.getDrawable(attr);
                    if (arrowDraw != null) {
                        arrowImg.setImageDrawable(arrowDraw);
                    }
                } else if (attr == R.styleable.WidgetText_wt_arrow_visible) {//是否显示箭头
                    boolean visibile = ta.getBoolean(attr, false);
                    if (visibile) {
                        arrowImg.setVisibility(View.VISIBLE);
                    } else {
                        arrowImg.setVisibility(View.GONE);
                    }
                } else if (attr == R.styleable.WidgetText_wt_is_selector) {//是否显示选中效果
                    boolean isSelector = ta.getBoolean(attr, false);
                    this.showSelector(isSelector);
                } else if (attr == R.styleable.WidgetText_wt_selector_bg) {//选中效果样式
                    Drawable arrowDraw = ta.getDrawable(attr);
                    if (arrowDraw != null) {
                        bgLayout.setBackgroundDrawable(arrowDraw);
                    }
                }
            }
            ta.recycle();
        }
    }

    public void setWidgetClickListener(IWidgetClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setDeleteClickBack(IWidgetClickBack deleteClickBack, int type) {
        this.deleteClickBack = deleteClickBack;
        this.type = type;
    }

    /**
     * 设置选中背景
     *
     * @param resId
     */
    public void setSelectorBg(int resId) {
        bgLayout.setBackgroundResource(resId);
    }

    /**
     * 是否显示默认选中背景
     *
     * @param isSelector true显示，false不显示
     */
    public void showSelector(boolean isSelector) {
        if (isSelector) {
            bgLayout.setBackgroundColor(context.getResources().getColor(R.color.form_selected));
        } else {
            bgLayout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        }
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
     * 设置提示内容
     *
     * @param resId
     */
    public void setContextHitValue(int resId) {
        this.contextTv.setHint(resId);
    }

    /**
     * 设置提示内容
     *
     * @param value
     */
    public void setContextHitValue(String value) {
        this.contextTv.setHint(value);
    }

    /**
     * 设置提示内容颜色
     *
     * @param color
     */
    public void setContextHitColor(int color) {
        this.contextTv.setHintTextColor(color);
    }

    /**
     * 是否显示删除按钮
     *
     * @param isVisibility
     */
    public void showDeleteImg(boolean isVisibility) {
        if (isVisibility) {
            deleteImg.setVisibility(View.VISIBLE);
        } else {
            deleteImg.setVisibility(View.GONE);
        }
    }

}
