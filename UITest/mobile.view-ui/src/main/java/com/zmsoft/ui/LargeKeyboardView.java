package com.zmsoft.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zmsoft.ui.listener.IKeyboardInput;


/**
 * 描述:自定义键盘（数字和字母加几个特殊字符，可用于登陆用户名、密码输入）
 * 日期:2012-10-10
 * 作者:郑华彬
 */
public class LargeKeyboardView implements OnClickListener {
    private LayoutInflater inflater;

    //父类容器
    private LinearLayout parentContainer;

    private View keyboardView;

    private Button btnQ, btnW, btnE, btnR, btnT, btnY, btnU, btnI, btnO, btnP;

    private Button btnA, btnS, btnD, btnF, btnG, btnH, btnJ, btnK, btnL;

    private Button btnZ, btnX, btnC, btnV, btnB, btnN, btnM;

    private Button numBtn1, numBtn2, numBtn3, numBtn4, numBtn5, numBtn6, numBtn7, numBtn8, numBtn9, numBtn0;

    private Button btnClear, btnBackspace, btnPoint, btnAt, btnUnderline, btnConfirm;

    private Button btnSlash, btnColon;

    private IKeyboardInput keyboardInput;

    //调用该键盘的元素（即放置键盘输入结果的元素）
    private Button invokeItem;

    private boolean hasClicked = false;

    public LargeKeyboardView(LinearLayout parentContainer, LayoutInflater inflater) {
        this.inflater = inflater;
        this.parentContainer = parentContainer;
        this.init();
    }

    private void init() {
        this.initMainView();
        this.initButtonView();
        this.initButtonEvent();
    }

    private void initMainView() {
        keyboardView = inflater.inflate(R.layout.custom_keyboard_view, null);
        parentContainer.addView(keyboardView);
    }

    //初始化按钮视图
    private void initButtonView() {
        btnQ = (Button) keyboardView.findViewById(R.id.btn_q);
        btnW = (Button) keyboardView.findViewById(R.id.btn_w);
        btnE = (Button) keyboardView.findViewById(R.id.btn_e);
        btnR = (Button) keyboardView.findViewById(R.id.btn_r);
        btnT = (Button) keyboardView.findViewById(R.id.btn_t);
        btnY = (Button) keyboardView.findViewById(R.id.btn_y);
        btnU = (Button) keyboardView.findViewById(R.id.btn_u);
        btnI = (Button) keyboardView.findViewById(R.id.btn_i);
        btnO = (Button) keyboardView.findViewById(R.id.btn_o);
        btnP = (Button) keyboardView.findViewById(R.id.btn_p);
        btnA = (Button) keyboardView.findViewById(R.id.btn_a);
        btnS = (Button) keyboardView.findViewById(R.id.btn_s);
        btnD = (Button) keyboardView.findViewById(R.id.btn_d);
        btnF = (Button) keyboardView.findViewById(R.id.btn_f);
        btnG = (Button) keyboardView.findViewById(R.id.btn_g);
        btnH = (Button) keyboardView.findViewById(R.id.btn_h);
        btnJ = (Button) keyboardView.findViewById(R.id.btn_j);
        btnK = (Button) keyboardView.findViewById(R.id.btn_k);
        btnL = (Button) keyboardView.findViewById(R.id.btn_l);
        btnZ = (Button) keyboardView.findViewById(R.id.btn_z);
        btnX = (Button) keyboardView.findViewById(R.id.btn_x);
        btnC = (Button) keyboardView.findViewById(R.id.btn_c);
        btnV = (Button) keyboardView.findViewById(R.id.btn_v);
        btnB = (Button) keyboardView.findViewById(R.id.btn_b);
        btnN = (Button) keyboardView.findViewById(R.id.btn_n);
        btnM = (Button) keyboardView.findViewById(R.id.btn_m);

        numBtn1 = (Button) keyboardView.findViewById(R.id.btn_1);
        numBtn2 = (Button) keyboardView.findViewById(R.id.btn_2);
        numBtn3 = (Button) keyboardView.findViewById(R.id.btn_3);
        numBtn4 = (Button) keyboardView.findViewById(R.id.btn_4);
        numBtn5 = (Button) keyboardView.findViewById(R.id.btn_5);
        numBtn6 = (Button) keyboardView.findViewById(R.id.btn_6);
        numBtn7 = (Button) keyboardView.findViewById(R.id.btn_7);
        numBtn8 = (Button) keyboardView.findViewById(R.id.btn_8);
        numBtn9 = (Button) keyboardView.findViewById(R.id.btn_9);
        numBtn0 = (Button) keyboardView.findViewById(R.id.btn_0);

        btnPoint = (Button) keyboardView.findViewById(R.id.btn_point);
        btnAt = (Button) keyboardView.findViewById(R.id.btn_at);
        btnUnderline = (Button) keyboardView.findViewById(R.id.btn_underline);

        btnClear = (Button) keyboardView.findViewById(R.id.btn_clear);
        btnBackspace = (Button) keyboardView.findViewById(R.id.btn_backspace);
        btnConfirm = (Button) keyboardView.findViewById(R.id.btn_confirm);

        btnSlash = (Button) keyboardView.findViewById(R.id.btn_slash);
        btnColon = (Button) keyboardView.findViewById(R.id.btn_colon);
    }

    //初始化各个按钮的点击事件
    private void initButtonEvent() {
        btnQ.setOnClickListener(this);
        btnW.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnR.setOnClickListener(this);
        btnT.setOnClickListener(this);
        btnY.setOnClickListener(this);
        btnU.setOnClickListener(this);
        btnI.setOnClickListener(this);
        btnO.setOnClickListener(this);
        btnP.setOnClickListener(this);
        btnA.setOnClickListener(this);
        btnS.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnF.setOnClickListener(this);
        btnG.setOnClickListener(this);
        btnH.setOnClickListener(this);
        btnJ.setOnClickListener(this);
        btnK.setOnClickListener(this);
        btnL.setOnClickListener(this);
        btnZ.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnV.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btnM.setOnClickListener(this);

        numBtn1.setOnClickListener(this);
        numBtn2.setOnClickListener(this);
        numBtn3.setOnClickListener(this);
        numBtn4.setOnClickListener(this);
        numBtn5.setOnClickListener(this);
        numBtn6.setOnClickListener(this);
        numBtn7.setOnClickListener(this);
        numBtn8.setOnClickListener(this);
        numBtn9.setOnClickListener(this);
        numBtn0.setOnClickListener(this);

        btnPoint.setOnClickListener(this);
        btnUnderline.setOnClickListener(this);
        btnSlash.setOnClickListener(this);
        btnColon.setOnClickListener(this);
        btnAt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputText("@");
                if (keyboardInput != null) {
                    keyboardInput.letterInput();
                }
            }
        });
        btnBackspace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (invokeItem != null) {
                    StringBuilder s = new StringBuilder();
                    s.append(invokeItem.getText().toString());
                    if (s.length() > 0) {
                        invokeItem.setText(s.deleteCharAt(s.length() - 1).toString());
                        if (keyboardInput != null) {
                            keyboardInput.letterInput();
                        }
                    }
                }
            }
        });
        btnClear.setOnClickListener(this);
        btnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyboardInput != null) {
                    keyboardInput.confirmInput();
                }
            }
        });
    }

    public void registeTarget(IKeyboardInput keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    public void registeTarget(IKeyboardInput keyboardInput, Button invokeItem) {
        this.keyboardInput = keyboardInput;
        this.invokeItem = invokeItem;
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String tag = (String) btn.getTag();
        setInputText(tag);
    }

    //在尾部追加字符
    private void setInputText(String tag) {
        if (invokeItem != null) {
            if (!"clear".equals(tag)) {
                if (hasClicked) {
                    String numStr = invokeItem.getText().toString();
                    invokeItem.setText(numStr + tag);
                } else {
                    hasClicked = true;
                    invokeItem.setText(tag);
                }
            } else {
                invokeItem.setText("");
            }
            if (keyboardInput != null) {
                keyboardInput.letterInput();
            }
        }
    }

    //设置是否点击过，用以第一次点击时，清空原有数据的判断
    public void setHasClicked(boolean hasClicked) {
        this.hasClicked = hasClicked;
    }

    public void setVisibility(int visibility) {
        this.keyboardView.setVisibility(visibility);
    }

    public Button getInvokeItem() {
        return invokeItem;
    }

    public void setInvokeItem(Button invokeItem) {
        this.invokeItem = invokeItem;
    }
}
