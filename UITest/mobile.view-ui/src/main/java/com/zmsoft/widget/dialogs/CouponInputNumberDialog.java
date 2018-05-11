/*************************
 * 版权声明 **********************************
 * 版权所有：Copyright (c) 2008, 2013 黄晓峰
 * <p>
 * 工程名称：	zmsoft.ui.widget
 * 创建者：	huangxf 创建日期： 2013-11-14
 * 创建记录：	创建类结构。
 * <p>
 * ************************* 变更记录 ********************************
 * 修改者：
 * 修改日期：
 * 修改记录：
 * <p>
 * <p>
 * ......************************* To Do List*****************************
 * <p>
 * <p>
 * Suberversion 信息
 * ID:			$Id$
 * 源代码URL：	$HeadURL$
 * 最后修改者：	$LastChangedBy$
 * 最后修改日期：	$LastChangedDate$
 * 最新版本：		$LastChangedRevision$
 **/

package com.zmsoft.widget.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zmsoft.ui.R;
import com.zmsoft.widget.listener.ITextCallback;


/**
 * 数字输入对话框针对优惠券码的输入.
 *
 * @author <a href="mailto:rain999@gmail.com">黄晓峰</a>.
 * @version $Revision$.
 */
public class CouponInputNumberDialog extends Dialog implements View.OnClickListener {
    private TextView cont = null;

    /**
     * <code>标题</code>.
     */
    private String title;

    /**
     * <code>说明文字</code>.
     */
    private String memo;
    /**
     * <code>最大输入长度</code>.
     */
    private int maxLength;

    /**
     * <code>原始文本</code>.
     */
    private String sourceText;

    private TextView inputSpec = null;
    /**
     * <code>文本回调</code>.
     */
    private ITextCallback textCallback;
    /**
     * <code>tip否需要重新赋值</code>
     */
    private boolean flag = true;
    /**
     * <code>类型</code>
     */
    private Short type;

    /**
     * <code>是否点击过</code>
     */
    protected boolean hasClicked = false;


    public CouponInputNumberDialog(Context context, String title, String sourceText, ITextCallback textCallback) {
        super(context, R.style.sDialog);
        this.title = title;
        this.sourceText = sourceText;
        this.textCallback = textCallback;
        this.hasClicked = false;
    }

    /**
     * 得到最大长度.
     *
     * @return 最大长度.
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * 设置最大长度.
     *
     * @param maxLength 最大长度.
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_number_dialog);
        TextView titl = (TextView) findViewById(R.id.title);
        cont = (TextView) findViewById(R.id.input_text);
        TextView tvMemo = (TextView) findViewById(R.id.memo);
        TextView txtTip = (TextView) findViewById(R.id.txt_tip);
        inputSpec = (TextView) findViewById(R.id.input_spec);

        titl.setText(title);
        cont.setText(sourceText);
        if (!TextUtils.isEmpty(memo)) {
            tvMemo.setText(memo);
            tvMemo.setVisibility(View.VISIBLE);
        } else {
            tvMemo.setVisibility(View.GONE);
        }

        txtTip.setVisibility(View.GONE);
        Button cancel = (Button) findViewById(R.id.btn_cancel);
        Button clear = (Button) findViewById(R.id.btn_clear);
        Button ok = (Button) findViewById(R.id.btn_ok);
        clear.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                cont.setText("");
            }
        });
        cancel.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                CouponInputNumberDialog.this.dismiss();
            }
        });
        ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponInputNumberDialog.this.dismiss();
                textCallback.onTextBack(cont.getText().toString(), type);
            }
        });

        cont.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String txtEnd = s.toString();
                if (txtEnd.length() > maxLength) {
                    inputSpec.setText(String.format(getContext().getString(R.string.number_tip_max_length), maxLength));
                    txtEnd = substringByCharacter(txtEnd, txtEnd.length() - 1);
                    cont.setText(txtEnd);
                    return;
                }
            }
        });

        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btn_dot = (Button) findViewById(R.id.btn_dot);
        Button btn_h = (Button) findViewById(R.id.btn_h);
        btn_h.setVisibility(View.INVISIBLE);
        btn_dot.setVisibility(View.INVISIBLE);
        Button btn_backspace = (Button) findViewById(R.id.btn_backspace);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_backspace
                .setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String txt = cont.getText().toString();
                        if (!TextUtils.isEmpty(txt)) {
                            cont.setText(txt.substring(0, txt.length() - 1));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        String txt = cont.getText().toString();
        if (TextUtils.isEmpty(txt)) {
            cont.setText(b.getText());
        } else {
            if (!hasClicked) {
                cont.setText(txt + b.getText());
                hasClicked = true;
            } else {
                cont.setText(txt + b.getText());
            }
        }
    }

    /**
     * 设置返回类型.
     *
     * @param type 返回类型.
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 根据给定的长度截取字符串，一个中文字符对应2个字符的长度
     *
     * @param s   源字符串.
     * @param len 要截取的长度
     * @return 截取后的字符串
     */
    private String substringByCharacter(String s, int len) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            int ascii = (int) c & 0xFFFF;
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
            if (length <= len) {
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();

    }
}
