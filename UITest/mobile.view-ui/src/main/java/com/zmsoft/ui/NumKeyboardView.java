package com.zmsoft.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zmsoft.ui.listener.IKeyboardInput;
import com.zmsoft.ui.listener.InputException;

/**
 * 描述:数字键盘组件，用以只能输入数字的情形
 * 日期:2012-09-21
 * 作者:邵建青
 */
public class NumKeyboardView {
    protected boolean hasClicked = false;
    private LayoutInflater inflater;
    private LinearLayout parentContainer;
    private View numKeyboardView;
    private Button numBtn1, numBtn2, numBtn3, numBtn4, numBtn5, numBtn6, numBtn7, numBtn8, numBtn9, numBtn0, numBtnPoint, numBtnClear;
    private Button backspaceBtn, confirmBtn;
    private TextView inputItem;
    private IKeyboardInput keyboardInput;

    public NumKeyboardView(LinearLayout parentContainer, LayoutInflater inflater, IKeyboardInput keyboardInput) {
        this.inflater = inflater;
        this.parentContainer = parentContainer;
        this.keyboardInput = keyboardInput;
        this.init();
    }

    private void init() {
        this.initMainView();
        this.initButtonView();
        this.initButtonEvent();
    }

    private void initMainView() {
        numKeyboardView = inflater.inflate(R.layout.num_keyboard_view, null);
        parentContainer.addView(numKeyboardView);
    }

    //初始化按钮视图
    private void initButtonView() {
        numBtn1 = (Button) numKeyboardView.findViewById(R.id.btn_1);
        numBtn2 = (Button) numKeyboardView.findViewById(R.id.btn_2);
        numBtn3 = (Button) numKeyboardView.findViewById(R.id.btn_3);
        numBtn4 = (Button) numKeyboardView.findViewById(R.id.btn_4);
        numBtn5 = (Button) numKeyboardView.findViewById(R.id.btn_5);
        numBtn6 = (Button) numKeyboardView.findViewById(R.id.btn_6);
        numBtn7 = (Button) numKeyboardView.findViewById(R.id.btn_7);
        numBtn8 = (Button) numKeyboardView.findViewById(R.id.btn_8);
        numBtn9 = (Button) numKeyboardView.findViewById(R.id.btn_9);
        numBtn0 = (Button) numKeyboardView.findViewById(R.id.btn_0);
        numBtnPoint = (Button) numKeyboardView.findViewById(R.id.btn_point);
        numBtnClear = (Button) numKeyboardView.findViewById(R.id.btn_clear);
        backspaceBtn = (Button) numKeyboardView.findViewById(R.id.btn_backspace);
        confirmBtn = (Button) numKeyboardView.findViewById(R.id.btn_confirm);
    }

    //初始化各个按钮的点击事件
    private void initButtonEvent() {
        NumBtnClickListener numBtnClickListener = new NumBtnClickListener();
        numBtn1.setOnClickListener(numBtnClickListener);
        numBtn2.setOnClickListener(numBtnClickListener);
        numBtn3.setOnClickListener(numBtnClickListener);
        numBtn4.setOnClickListener(numBtnClickListener);
        numBtn5.setOnClickListener(numBtnClickListener);
        numBtn6.setOnClickListener(numBtnClickListener);
        numBtn7.setOnClickListener(numBtnClickListener);
        numBtn8.setOnClickListener(numBtnClickListener);
        numBtn9.setOnClickListener(numBtnClickListener);
        numBtn0.setOnClickListener(numBtnClickListener);
        numBtnPoint.setOnClickListener(numBtnClickListener);
        numBtnClear.setOnClickListener(numBtnClickListener);

        backspaceBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputItem != null) {
                    StringBuilder s = new StringBuilder();
                    s.append(inputItem.getText().toString());
                    if (s.length() > 0) {
                        inputItem.setText(s.deleteCharAt(s.length() - 1).toString());
                    }
                    if (keyboardInput != null) {
                        keyboardInput.letterInput();
                    }
                }
            }
        });

        confirmBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyboardInput != null) {
                    keyboardInput.confirmInput();
                }
            }
        });
    }

    //在尾部追加数字
    private void input(String num) {
        if (inputItem != null) {
            if (!"clear".equals(num)) {
                if (hasClicked) {
                    String numStr = inputItem.getText().toString();
                    inputItem.setText(numStr + num);
                } else {
                    hasClicked = true;
                    inputItem.setText(num);
                }
            } else {
                inputItem.setText("");
            }
            if (keyboardInput != null) {
                keyboardInput.letterInput();
            }
        }
    }

    public String getString() throws InputException {
        if (!TextUtils.isEmpty(inputItem.getText())) {
            return inputItem.getText().toString();
        } else {
            throw new InputException();
        }
    }

    //设置是否点击过，用以第一次点击时，清空原有数据的判断
    public void setHasClicked(boolean hasClicked) {
        this.hasClicked = hasClicked;
    }

    public void setInputItem(TextView inputItem) {
        this.inputItem = inputItem;
    }

    public void setVisibility(int visibility) {
        this.numKeyboardView.setVisibility(visibility);
    }

    //数字按钮点击事件处理结果
    private class NumBtnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            String tag = (String) btn.getTag();
            input(tag);
        }
    }
}
