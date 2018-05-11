package com.zmsoft.widget.switchbtn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.zmsoft.ui.R;
import com.zmsoft.widget.CommonItem;
import com.zmsoft.widget.ICommonItemType;

/**
 * 描述 ：开关
 * Created by huangyu on 2017/8/7.
 *
 * @version v1.0
 */

public class WidgetBaseSwichBtn extends CommonItem implements CompoundButton.OnClickListener {

    protected ToggleButton mBoolBtn;
    protected TextView mTxtMemo;
    protected ImageView mViewChild;
    /**
     * <code>原始值</code>.
     */
    protected boolean oldBoolean;
    /**
     * <code>修改值</code>.
     * 点击后newBoolean置不变，在调用switchOnClick()方法后才改变
     */
    protected boolean newBoolean;
    /**
     * <code>点击后的改变值</code>.
     */
    protected boolean changeBoolean = false;


    private boolean isClickable = true; //是否可点击

    public WidgetBaseSwichBtn(Context context) {
        super(context);
    }

    public WidgetBaseSwichBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetBaseSwichBtn(Context context, AttributeSet attrs, int defStyle) {
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
        View view =  LayoutInflater.from(context).inflate(R.layout.widget_switch_btn, this, true);
        mBoolBtn = (ToggleButton) view.findViewById(R.id.boolBtn);
        mTxtMemo = (TextView) view.findViewById(R.id.txtMemo);
        mViewChild = (ImageView) view.findViewById(R.id.viewChild);
        return view;
    }
    /**
     * 初始化页面设值.
     */
    @Override
    public void loadinit() {
        mBoolBtn.setOnClickListener(this);
        if (memo != -1) {
            mTxtMemo.setVisibility(View.VISIBLE);
            mTxtMemo.setText(memo);
        }
        if (memo_color != -1) {
            mTxtMemo.setTextColor(memo_color);
        }
        if (child) {
            mViewChild.setVisibility(View.VISIBLE);
        }
    }

    public void setOldValue(Boolean value){
        if (value == null) {
            value = false;
        }
        mBoolBtn.setChecked(value);
        oldBoolean = value;
        newBoolean = value;
    }

    public boolean getChangeValue() {
        return changeBoolean;
    }

    public String getChangeText() {
        if (changeBoolean) {
            return  "1";
        } else {
            return  "0";
        }
    }

    public boolean getNewValue() {
        return newBoolean;
    }

    public String getNewText() {
        if (newBoolean) {
            newValue = "1";
        } else {
            newValue = "0";
        }
        return newValue;
    }

    //设置按钮是否可被点击
    public void setIsClickable(boolean isClickable){
        this.isClickable = isClickable;
    }

    public ToggleButton getmBoolBtn() {
        return mBoolBtn;
    }

    public TextView getTxtMemo() {
        return mTxtMemo;
    }

    public void setmBoolBtn(ToggleButton mBoolBtn) {
        this.mBoolBtn = mBoolBtn;
    }

    @Override
    public void onClick(View v) {
        mBoolBtn.setChecked(!mBoolBtn.isChecked());//先置成原来的状态
        if(isClickable){
            changeBoolean = !mBoolBtn.isChecked();//因为收银业务需要，点击按钮时，先保持原来的状态，在网络请求完毕后在更新状态
            if (controlListener != null) {
                controlListener.onControlEditCallBack(this, oldBoolean, changeBoolean);
            }
        }
    }

    public void switchOnClick(){
        mBoolBtn.setChecked(changeBoolean);
        newBoolean = mBoolBtn.isChecked();
        changeBoolean = !changeBoolean;
    }

    /**
     * 设置输入格式
     * @param showType
     */
    public void setInputTypeShow(int showType){
        this.inputType = showType;
        if(inputType == ICommonItemType.SHOW_ONLY){
            mBoolBtn.setClickable(false);
        }
    }

    /**
     * 判断内容是否改变
     *
     * @return
     */
    public boolean isChanged() {
        return !newBoolean == oldBoolean;
    }

}
